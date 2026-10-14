package com.ragaroads.services;

import com.ragaroads.models.Concert;

import java.util.List;

public interface ConcertService {
    Concert getConcertById(Long concertId);
    List<Concert> getAllConcerts();
}
