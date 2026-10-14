package com.ragaroads.dtos;

public class BookingRequest {

    private Long concertId;
    private Long artistId;

    public BookingRequest() {}

    public BookingRequest(Long concertId, Long artistId) {
        this.concertId = concertId;
        this.artistId = artistId;
    }

    public Long getConcertId() { return concertId; }
    public void setConcertId(Long concertId) { this.concertId = concertId; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
}
