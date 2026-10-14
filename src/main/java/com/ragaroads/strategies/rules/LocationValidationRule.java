package com.ragaroads.strategies.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import org.springframework.stereotype.Component;

@Component
public class LocationValidationRule {

    public AllocationResult validate(Artist artist, Concert concert) {
        if (artist.getCity().equalsIgnoreCase(concert.getConcertCity())) {
            return AllocationResult.confirmed(
                "Artist is in the same city: " + artist.getCity()
            );
        }
        if (artist.getState().equalsIgnoreCase(concert.getConcertState())) {
            return AllocationResult.confirmed(
                "Artist is in the same state: " + artist.getState()
                + " (different city: " + artist.getCity() + ")"
            );
        }
        return AllocationResult.rejected(
            "Artist location (" + artist.getCity() + ", " + artist.getState()
            + ") is outside the concert state (" + concert.getConcertState() + ")"
        );
    }
}
