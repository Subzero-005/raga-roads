package com.ragaroads.strategies;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import com.ragaroads.strategies.rules.LocationValidationRule;
import com.ragaroads.strategies.rules.RoleValidationRule;

public class BalancedAllocationStrategy implements AllocationStrategy {

    private final RoleValidationRule roleRule;
    private final LocationValidationRule locationRule;
    private final AvailabilityValidationRule availabilityRule;
    private final BudgetValidationRule budgetRule;

    public BalancedAllocationStrategy(RoleValidationRule roleRule,
                                      LocationValidationRule locationRule,
                                      AvailabilityValidationRule availabilityRule,
                                      BudgetValidationRule budgetRule) {
        this.roleRule = roleRule;
        this.locationRule = locationRule;
        this.availabilityRule = availabilityRule;
        this.budgetRule = budgetRule;
    }

    @Override
    public AllocationResult allocate(Artist artist, Concert concert) {
        AllocationResult roleResult = roleRule.validate(artist, concert);
        if (!roleResult.isConfirmed()) return roleResult;

        AllocationResult locationResult = locationRule.validate(artist, concert);
        if (!locationResult.isConfirmed()) return locationResult;

        AllocationResult availabilityResult = availabilityRule.validate(artist, concert);
        if (!availabilityResult.isConfirmed()) return availabilityResult;

        AllocationResult budgetResult = budgetRule.validate(artist, concert);
        if (!budgetResult.isConfirmed()) return budgetResult;

        return AllocationResult.confirmed(
            "Balanced strategy: all validations passed for artist " + artist.getArtistName()
        );
    }

    @Override
    public String getStrategyName() {
        return "BALANCED";
    }
}
