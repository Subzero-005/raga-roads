package com.ragaroads.strategies;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import com.ragaroads.strategies.rules.RoleValidationRule;

public class AvailabilityAllocationStrategy implements AllocationStrategy {

    private final RoleValidationRule roleRule;
    private final AvailabilityValidationRule availabilityRule;
    private final BudgetValidationRule budgetRule;

    public AvailabilityAllocationStrategy(RoleValidationRule roleRule,
                                          AvailabilityValidationRule availabilityRule,
                                          BudgetValidationRule budgetRule) {
        this.roleRule = roleRule;
        this.availabilityRule = availabilityRule;
        this.budgetRule = budgetRule;
    }

    @Override
    public AllocationResult allocate(Artist artist, Concert concert) {
        AllocationResult roleResult = roleRule.validate(artist, concert);
        if (!roleResult.isConfirmed()) return roleResult;

        AllocationResult availabilityResult = availabilityRule.validate(artist, concert);
        if (!availabilityResult.isConfirmed()) return availabilityResult;

        AllocationResult budgetResult = budgetRule.validate(artist, concert);
        if (!budgetResult.isConfirmed()) return budgetResult;

        return AllocationResult.confirmed(
            "Availability strategy: role, availability and budget validated for artist " + artist.getArtistName()
        );
    }

    @Override
    public String getStrategyName() {
        return "AVAILABILITY";
    }
}
