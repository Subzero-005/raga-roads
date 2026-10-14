package com.ragaroads.models;

public class Artist {

    private Long artistId;
    private String artistName;
    private String artistRole;
    private String city;
    private String state;
    private Double performanceCost;
    private Boolean available;

    public Artist() {}

    public Artist(Long artistId, String artistName, String artistRole,
                  String city, String state, Double performanceCost, Boolean available) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistRole = artistRole;
        this.city = city;
        this.state = state;
        this.performanceCost = performanceCost;
        this.available = available;
    }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getArtistRole() { return artistRole; }
    public void setArtistRole(String artistRole) { this.artistRole = artistRole; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Double getPerformanceCost() { return performanceCost; }
    public void setPerformanceCost(Double performanceCost) { this.performanceCost = performanceCost; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
