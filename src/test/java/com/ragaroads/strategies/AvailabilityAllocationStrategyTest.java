package com.ragaroads.strategies;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import com.ragaroads.strategies.rules.RoleValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityAllocationStrategyTest {

    private AvailabilityAllocationStrategy strategy;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        strategy = new AvailabilityAllocationStrategy(
            new RoleValidationRule(),
            new AvailabilityValidationRule(),
            new BudgetValidationRule()
        );
        // Artist is from a completely different state — location is ignored in this strategy
        artist = new Artist(1L, "Rahul", "Singer-Male", "Mumbai", "Maharashtra", 14000.0, true);
        concert = new Concert(2L, "Corporate Event Delhi", "2026-08-20", "18:00",
                "Delhi", "Delhi", "AVAILABILITY", "Singer-Male", 16000.0);
    }

    @Test
    void shouldConfirmRegardlessOfLocation() {
        AllocationResult result = strategy.allocate(artist, concert);
        assertTrue(result.isConfirmed(), "Availability strategy should ignore location");
    }

    @Test
    void shouldRejectWhenRoleDoesNotMatch() {
        concert.setArtistRole("Drummer");
        AllocationResult result = strategy.allocate(artist, concert);
        assertFalse(result.isConfirmed());
        assertTrue(result.getReason().contains("Role mismatch"));
    }

    @Test
    void shouldRejectWhenArtistIsUnavailable() {
        artist.setAvailable(false);
        AllocationResult result = strategy.allocate(artist, concert);
        assertFalse(result.isConfirmed());
        assertTrue(result.getReason().contains("not available"));
    }

    @Test
    void shouldRejectWhenCostExceedsBudget() {
        artist.setPerformanceCost(20000.0);
        AllocationResult result = strategy.allocate(artist, concert);
        assertFalse(result.isConfirmed());
        assertTrue(result.getReason().contains("exceeds allocated budget"));
    }

    @Test
    void shouldConfirmWhenRoleAvailabilityAndBudgetAllPass() {
        AllocationResult result = strategy.allocate(artist, concert);
        assertTrue(result.isConfirmed());
        assertEquals("Confirmed", result.getStatus());
    }

    @Test
    void shouldReturnStrategyName() {
        assertEquals("AVAILABILITY", strategy.getStrategyName());
    }
}
