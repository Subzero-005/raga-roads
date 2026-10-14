package com.ragaroads.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.RoleValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleValidationTest {

    private RoleValidationRule rule;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        rule = new RoleValidationRule();
        artist = new Artist(1L, "Arjun", "Singer-Male", "Chennai", "Tamil Nadu", 12000.0, true);
        concert = new Concert(1L, "Corporate Night Chennai", "2026-08-01", "18:00",
                "Chennai", "Tamil Nadu", "BALANCED", "Singer-Male", 15000.0);
    }

    @Test
    void shouldConfirmWhenRoleMatches() {
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldRejectWhenRoleDoesNotMatch() {
        concert.setArtistRole("Guitarist");
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
        assertEquals("Rejected", result.getStatus());
        assertTrue(result.getReason().contains("Role mismatch"));
    }

    @Test
    void shouldConfirmCaseInsensitiveRoleMatch() {
        artist.setArtistRole("singer-male");
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldRejectWithDifferentRoles() {
        artist.setArtistRole("Drummer");
        concert.setArtistRole("Flutist");
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
    }
}
