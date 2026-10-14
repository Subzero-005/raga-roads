package com.ragaroads.models;

public class Concert {

    private Long concertId;
    private String concertTitle;
    private String concertDate;
    private String concertTime;
    private String concertCity;
    private String concertState;
    private String strategyType;
    private String artistRole;
    private Double budgetAllocated;

    public Concert() {}

    public Concert(Long concertId, String concertTitle, String concertDate, String concertTime,
                   String concertCity, String concertState, String strategyType,
                   String artistRole, Double budgetAllocated) {
        this.concertId = concertId;
        this.concertTitle = concertTitle;
        this.concertDate = concertDate;
        this.concertTime = concertTime;
        this.concertCity = concertCity;
        this.concertState = concertState;
        this.strategyType = strategyType;
        this.artistRole = artistRole;
        this.budgetAllocated = budgetAllocated;
    }

    public Long getConcertId() { return concertId; }
    public void setConcertId(Long concertId) { this.concertId = concertId; }

    public String getConcertTitle() { return concertTitle; }
    public void setConcertTitle(String concertTitle) { this.concertTitle = concertTitle; }

    public String getConcertDate() { return concertDate; }
    public void setConcertDate(String concertDate) { this.concertDate = concertDate; }

    public String getConcertTime() { return concertTime; }
    public void setConcertTime(String concertTime) { this.concertTime = concertTime; }

    public String getConcertCity() { return concertCity; }
    public void setConcertCity(String concertCity) { this.concertCity = concertCity; }

    public String getConcertState() { return concertState; }
    public void setConcertState(String concertState) { this.concertState = concertState; }

    public String getStrategyType() { return strategyType; }
    public void setStrategyType(String strategyType) { this.strategyType = strategyType; }

    public String getArtistRole() { return artistRole; }
    public void setArtistRole(String artistRole) { this.artistRole = artistRole; }

    public Double getBudgetAllocated() { return budgetAllocated; }
    public void setBudgetAllocated(Double budgetAllocated) { this.budgetAllocated = budgetAllocated; }
}
