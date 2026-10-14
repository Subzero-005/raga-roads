package com.ragaroads.repositories;

import com.ragaroads.models.Booking;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@Profile("!jdbc")
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Long, Booking> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Booking save(Booking booking) {
        if (booking.getBookingId() == null) {
            booking.setBookingId(idSequence.getAndIncrement());
        }
        store.put(booking.getBookingId(), booking);
        return booking;
    }

    @Override
    public Optional<Booking> findById(Long bookingId) {
        return Optional.ofNullable(store.get(bookingId));
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Booking> findByConcertId(Long concertId) {
        return store.values().stream()
                .filter(b -> b.getConcertId().equals(concertId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isArtistBlocked(Long artistId) {
        // An artist is blocked if they have 3 or more rejected bookings
        long rejectedCount = store.values().stream()
                .filter(b -> b.getArtistId().equals(artistId) && "Rejected".equals(b.getBookingStatus()))
                .count();
        return rejectedCount >= 3;
    }
}
