package com.ragaroads.strategies.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import org.springframework.stereotype.Component;

@Component
public class BudgetValidationRule {

    public AllocationResult validate(Artist artist, Concert concert) {
        if (artist.getPerformanceCost() <= concert.getBudgetAllocated()) {
            double remaining = concert.getBudgetAllocated() - artist.getPerformanceCost();
            return AllocationResult.confirmed(
                "Artist cost " + artist.getPerformanceCost()
                + " is within budget " + concert.getBudgetAllocated()
                + ". Remaining: " + remaining
            );
        }
        return AllocationResult.rejected(
            "Artist cost " + artist.getPerformanceCost()
            + " exceeds allocated budget " + concert.getBudgetAllocated()
        );
    }
}
