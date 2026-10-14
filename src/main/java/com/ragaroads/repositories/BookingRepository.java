package com.ragaroads.repositories;

import com.ragaroads.models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Booking save(Booking booking);
    Optional<Booking> findById(Long bookingId);
    List<Booking> findAll();
    List<Booking> findByConcertId(Long concertId);
    boolean isArtistBlocked(Long artistId);
}
