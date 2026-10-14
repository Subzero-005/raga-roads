package com.ragaroads.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityValidationTest {

    private AvailabilityValidationRule rule;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        rule = new AvailabilityValidationRule();
        artist = new Artist(1L, "Arjun", "Singer-Male", "Chennai", "Tamil Nadu", 12000.0, true);
        concert = new Concert(1L, "Corporate Night Chennai", "2026-08-01", "18:00",
                "Chennai", "Tamil Nadu", "BALANCED", "Singer-Male", 15000.0);
    }

    @Test
    void shouldConfirmWhenArtistIsAvailable() {
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldRejectWhenArtistIsUnavailable() {
        artist.setAvailable(false);
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
        assertEquals("Rejected", result.getStatus());
        assertTrue(result.getReason().contains("not available"));
    }

    @Test
    void shouldRejectWhenAvailableIsNull() {
        artist.setAvailable(null);
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
    }
}
