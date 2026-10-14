package com.ragaroads.repositories;

import com.ragaroads.models.Booking;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JdbcBookingRepository implements BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Booking> rowMapper = (rs, rowNum) -> {
        Booking booking = new Booking();
        booking.setBookingId(rs.getLong("booking_id"));
        booking.setConcertId(rs.getLong("concert_id"));
        booking.setArtistId(rs.getLong("artist_id"));
        booking.setBookingStatus(rs.getString("booking_status"));
        booking.setStrategyApplied(rs.getString("strategy_applied"));
        Date date = rs.getDate("booking_date");
        if (date != null) booking.setBookingDate(date.toLocalDate());
        booking.setRejectionReason(rs.getString("rejection_reason"));
        return booking;
    };

    @Override
    public Booking save(Booking booking) {
        String sql = "INSERT INTO bookings (concert_id, artist_id, booking_status, strategy_applied, booking_date, rejection_reason) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, booking.getConcertId());
            ps.setLong(2, booking.getArtistId());
            ps.setString(3, booking.getBookingStatus());
            ps.setString(4, booking.getStrategyApplied());
            ps.setDate(5, Date.valueOf(booking.getBookingDate()));
            ps.setString(6, booking.getRejectionReason());
            return ps;
        }, keyHolder);
        booking.setBookingId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return booking;
    }

    @Override
    public Optional<Booking> findById(Long bookingId) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        List<Booking> results = jdbcTemplate.query(sql, rowMapper, bookingId);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Booking> findAll() {
        return jdbcTemplate.query("SELECT * FROM bookings", rowMapper);
    }

    @Override
    public List<Booking> findByConcertId(Long concertId) {
        return jdbcTemplate.query("SELECT * FROM bookings WHERE concert_id = ?", rowMapper, concertId);
    }

    @Override
    public boolean isArtistBlocked(Long artistId) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE artist_id = ? AND booking_status = 'Rejected'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, artistId);
        return count != null && count >= 3;
    }
}
