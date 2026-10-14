package com.ragaroads.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetValidationTest {

    private BudgetValidationRule rule;
    private Artist artist;
    private Concert concert;

    @BeforeEach
    void setUp() {
        rule = new BudgetValidationRule();
        artist = new Artist(1L, "Arjun", "Singer-Male", "Chennai", "Tamil Nadu", 12000.0, true);
        concert = new Concert(1L, "Corporate Night Chennai", "2026-08-01", "18:00",
                "Chennai", "Tamil Nadu", "BALANCED", "Singer-Male", 15000.0);
    }

    @Test
    void shouldConfirmWhenCostIsWithinBudget() {
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldConfirmWhenCostEqualsExactBudget() {
        artist.setPerformanceCost(15000.0);
        AllocationResult result = rule.validate(artist, concert);
        assertTrue(result.isConfirmed());
    }

    @Test
    void shouldRejectWhenCostExceedsBudget() {
        artist.setPerformanceCost(18000.0);
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
        assertEquals("Rejected", result.getStatus());
        assertTrue(result.getReason().contains("exceeds allocated budget"));
    }

    @Test
    void shouldRejectWhenCostSlightlyExceedsBudget() {
        artist.setPerformanceCost(15001.0);
        AllocationResult result = rule.validate(artist, concert);
        assertFalse(result.isConfirmed());
    }
}
