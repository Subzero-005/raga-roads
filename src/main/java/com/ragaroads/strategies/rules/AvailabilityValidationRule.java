package com.ragaroads.strategies.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityValidationRule {

    public AllocationResult validate(Artist artist, Concert concert) {
        if (Boolean.TRUE.equals(artist.getAvailable())) {
            return AllocationResult.confirmed("Artist " + artist.getArtistName() + " is available");
        }
        return AllocationResult.rejected(
            "Artist " + artist.getArtistName() + " is not available on " + concert.getConcertDate()
        );
    }
}
