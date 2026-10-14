package com.ragaroads.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.LocationValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationValidationTest {

    private LocationValidationRule rule;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        rule = new LocationValidationRule();
        artist = new Artist(1L, "Arjun", "Singer-Male", "Chennai", "Tamil Nadu", 12000.0, true);
        concert = new Concert(1L, "Corporate Night Chennai", "2026-08-01", "18:00",
                "Chennai", "Tamil Nadu", "BALANCED", "Singer-Male", 15000.0);
    }

    @Test
    void shouldConfirmWhenArtistIsInSameCity() {
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
        assertTrue(result.getReason().contains("same city"));
    }

    @Test
    void shouldConfirmWhenArtistIsInSameStateDifferentCity() {
        artist.setCity("Coimbatore");
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
        assertTrue(result.getReason().contains("same state"));
    }

    @Test
    void shouldRejectWhenArtistIsOutsideState() {
        artist.setCity("Mumbai");
        artist.setState("Maharashtra");
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
        assertEquals("Rejected", result.getStatus());
        assertTrue(result.getReason().contains("outside the concert state"));
    }

    @Test
    void shouldRejectWhenBothCityAndStateDiffer() {
        artist.setCity("Delhi");
        artist.setState("Delhi");
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
    }
}
