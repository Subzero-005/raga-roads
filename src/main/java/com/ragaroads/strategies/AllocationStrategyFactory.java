package com.ragaroads.strategies;

import com.ragaroads.strategies.rules.AvailabilityValidationRule;
import com.ragaroads.strategies.rules.BudgetValidationRule;
import com.ragaroads.strategies.rules.LocationValidationRule;
import com.ragaroads.strategies.rules.RoleValidationRule;
import org.springframework.stereotype.Component;

@Component
public class AllocationStrategyFactory {

    private final RoleValidationRule roleRule;
    private final LocationValidationRule locationRule;
    private final AvailabilityValidationRule availabilityRule;
    private final BudgetValidationRule budgetRule;

    public AllocationStrategyFactory(RoleValidationRule roleRule,
                                     LocationValidationRule locationRule,
                                     AvailabilityValidationRule availabilityRule,
                                     BudgetValidationRule budgetRule) {
        this.roleRule = roleRule;
        this.locationRule = locationRule;
        this.availabilityRule = availabilityRule;
        this.budgetRule = budgetRule;
    }

    public AllocationStrategy getStrategy(String strategyType) {
        if (strategyType == null) {
            throw new IllegalArgumentException("Strategy type cannot be null");
        }
        return switch (strategyType.toUpperCase()) {
            case "BALANCED" -> new BalancedAllocationStrategy(roleRule, locationRule, availabilityRule, budgetRule);
            case "AVAILABILITY" -> new AvailabilityAllocationStrategy(roleRule, availabilityRule, budgetRule);
            default -> throw new IllegalArgumentException("Unknown allocation strategy: " + strategyType);
        };
    }
}
