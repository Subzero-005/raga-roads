package com.ragaroads.services;

import com.ragaroads.dtos.BookingRequest;
import com.ragaroads.dtos.BookingResponse;
import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Booking;
import com.ragaroads.models.Concert;
import com.ragaroads.repositories.BookingRepository;
import com.ragaroads.strategies.AllocationStrategy;
import com.ragaroads.strategies.AllocationStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final ArtistService artistService;
    private final ConcertService concertService;
    private final BookingRepository bookingRepository;
    private final AllocationStrategyFactory strategyFactory;

    public BookingServiceImpl(ArtistService artistService,
                              ConcertService concertService,
                              BookingRepository bookingRepository,
                              AllocationStrategyFactory strategyFactory) {
        this.artistService = artistService;
        this.concertService = concertService;
        this.bookingRepository = bookingRepository;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public BookingResponse allocateArtist(BookingRequest request) {
        if (request.getArtistId() == null || request.getConcertId() == null) {
            throw new IllegalArgumentException("Both artistId and concertId are required");
        }
        log.info("Allocating artist {} for concert {}", request.getArtistId(), request.getConcertId());

        Artist artist = artistService.getArtistById(request.getArtistId());
        Concert concert = concertService.getConcertById(request.getConcertId());

        AllocationStrategy strategy = strategyFactory.getStrategy(concert.getStrategyType());
        AllocationResult result = strategy.allocate(artist, concert);

        Booking booking = new Booking(
            null,
            concert.getConcertId(),
            artist.getArtistId(),
            result.getStatus(),
            strategy.getStrategyName(),
            LocalDate.now(),
            result.isConfirmed() ? null : result.getReason()
        );
        booking = bookingRepository.save(booking);

        log.info("Booking {} created with status {}", booking.getBookingId(), booking.getBookingStatus());

        return buildResponse(booking, artist, concert);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
            .map(b -> {
                Artist artist = artistService.getArtistById(b.getArtistId());
                Concert concert = concertService.getConcertById(b.getConcertId());
                return buildResponse(b, artist, concert);
            })
            .collect(Collectors.toList());
    }

    private BookingResponse buildResponse(Booking booking, Artist artist, Concert concert) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setBookingStatus(booking.getBookingStatus());
        response.setRejectionReason(booking.getRejectionReason());
        response.setStrategyApplied(booking.getStrategyApplied());
        response.setBookingDate(booking.getBookingDate().toString());

        response.setConcertId(concert.getConcertId());
        response.setConcertTitle(concert.getConcertTitle());
        response.setConcertDate(concert.getConcertDate());
        response.setConcertTime(concert.getConcertTime());
        response.setConcertCity(concert.getConcertCity());
        response.setConcertState(concert.getConcertState());

        response.setArtistId(artist.getArtistId());
        response.setArtistName(artist.getArtistName());
        response.setArtistRole(artist.getArtistRole());

        double budget = concert.getBudgetAllocated();
        double cost = artist.getPerformanceCost();
        response.setBudgetAllocated(budget);
        response.setArtistCost(cost);
        response.setBudgetRemaining("Confirmed".equals(booking.getBookingStatus()) ? budget - cost : null);

        return response;
    }
}
