package com.ragaroads.strategies;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;

public interface AllocationStrategy {
    AllocationResult allocate(Artist artist, Concert concert);
    String getStrategyName();
}
