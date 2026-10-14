package com.ragaroads.models;

import java.time.LocalDate;

public class Booking {

    private Long bookingId;
    private Long concertId;
    private Long artistId;
    private String bookingStatus;
    private String strategyApplied;
    private LocalDate bookingDate;
    private String rejectionReason;

    public Booking() {}

    public Booking(Long bookingId, Long concertId, Long artistId, String bookingStatus,
                   String strategyApplied, LocalDate bookingDate, String rejectionReason) {
        this.bookingId = bookingId;
        this.concertId = concertId;
        this.artistId = artistId;
        this.bookingStatus = bookingStatus;
        this.strategyApplied = strategyApplied;
        this.bookingDate = bookingDate;
        this.rejectionReason = rejectionReason;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getConcertId() { return concertId; }
    public void setConcertId(Long concertId) { this.concertId = concertId; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public String getStrategyApplied() { return strategyApplied; }
    public void setStrategyApplied(String strategyApplied) { this.strategyApplied = strategyApplied; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
