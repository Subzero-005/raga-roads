package com.ragaroads.strategies.rules;

import com.ragaroads.models.AllocationResult;
import com.ragaroads.models.Artist;
import com.ragaroads.models.Concert;
import org.springframework.stereotype.Component;

@Component
public class RoleValidationRule {

    public AllocationResult validate(Artist artist, Concert concert) {
        if (artist.getArtistRole().equalsIgnoreCase(concert.getArtistRole())) {
            return AllocationResult.confirmed("Role matches: " + artist.getArtistRole());
        }
        return AllocationResult.rejected(
            "Role mismatch: artist role '" + artist.getArtistRole()
            + "' does not match required role '" + concert.getArtistRole() + "'"
        );
    }
}
