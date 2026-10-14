package com.ragaroads.strategies;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import com.ragaroads.strategies.rules.LocationValidationRule;
import com.ragaroads.strategies.rules.RoleValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedAllocationStrategyTest {

    private BalancedAllocationStrategy strategy;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        strategy = new BalancedAllocationStrategy(
            new RoleValidationRule(),
            new LocationValidationRule(),
            new AvailabilityValidationRule(),
            new BudgetValidationRule()
        );
        artist = new Artist(1L, "Arjun", "Singer-Male", "Chennai", "Tamil Nadu", 12000.0, true);
        concert = new Concert(1L, "Corporate Night Chennai", "2026-08-01", "18:00",
                "Chennai", "Tamil Nadu", "BALANCED", "Singer-Male", 15000.0);
    }

    @Test
    void shouldConfirmWhenAllValidationsPass() {
        AllocationResult result = strategy.allocate(artist, concert);
        assertTrue(result.isConfirmed());
        assertEquals("Confirmed", result.getStatus());
    }

    @Test
    void shouldRejectWhenRoleDoesNotMatch() {
        concert.setArtistRole("Guitarist");
        AllocationResult result = strategy.allocate(artist, concert);
        assertFalse(result.isConfirmed());
        assertTrue(result.getReason().contains("Role mismatch"));
    }

    @Test
    void shouldRejectWhenArtistIsOutsideState() {
        artist.setCity("Mumbai");
        artist.setState("Maharashtra");
        AllocationResult result = strategy.allocate(artist, concert);
        assertFalse(result.isConfirmed());
        assertTrue(result.getReason().contains("outside the concert state"));
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
    void shouldConfirmWhenArtistIsInSameStateDifferentCity() {
        artist.setCity("Coimbatore");
        AllocationResult result = strategy.allocate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldReturnStrategyName() {
        assertEquals("BALANCED", strategy.getStrategyName());
    }
}
