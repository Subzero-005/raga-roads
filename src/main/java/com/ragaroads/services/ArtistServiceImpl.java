package com.ragaroads.services;

import com.ragaroads.exceptions.ArtistNotFoundException;
import com.ragaroads.models.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArtistServiceImpl implements ArtistService {

    private static final Map<Long, Artist> ARTISTS = Map.ofEntries(
        Map.entry(1L,  new Artist(1L,  "Arjun",   "Singer-Male",   "Chennai",     "Tamil Nadu",  12000.0, true)),
        Map.entry(2L,  new Artist(2L,  "Rahul",   "Singer-Male",   "Bangalore",   "Karnataka",   15000.0, true)),
        Map.entry(3L,  new Artist(3L,  "Priya",   "Singer-Female", "Chennai",     "Tamil Nadu",  10000.0, true)),
        Map.entry(4L,  new Artist(4L,  "Megha",   "Singer-Female", "Hyderabad",   "Telangana",   12000.0, false)),
        Map.entry(5L,  new Artist(5L,  "Karthik", "Guitarist",     "Bangalore",   "Karnataka",    9000.0, true)),
        Map.entry(6L,  new Artist(6L,  "Vikram",  "Guitarist",     "Mumbai",      "Maharashtra", 11000.0, true)),
        Map.entry(7L,  new Artist(7L,  "Rohit",   "Drummer",       "Delhi",       "Delhi",        8500.0, true)),
        Map.entry(8L,  new Artist(8L,  "Amit",    "Drummer",       "Pune",        "Maharashtra",  9500.0, false)),
        Map.entry(9L,  new Artist(9L,  "Manoj",   "Keyboardist",   "Hyderabad",   "Telangana",   10000.0, true)),
        Map.entry(10L, new Artist(10L, "Suresh",  "Keyboardist",   "Chennai",     "Tamil Nadu",  11000.0, true)),
        Map.entry(11L, new Artist(11L, "Naveen",  "Flutist",       "Mysore",      "Karnataka",   12000.0, true)),
        Map.entry(12L, new Artist(12L, "Kiran",   "Flutist",       "Coimbatore",  "Tamil Nadu",  13000.0, true)),
        Map.entry(13L, new Artist(13L, "Ramesh",  "Tablist",       "Mumbai",      "Maharashtra",  7000.0, true)),
        Map.entry(14L, new Artist(14L, "Sanjay",  "Tablist",       "Jaipur",      "Rajasthan",    8000.0, true))
    );

    @Override
    public Artist getArtistById(Long artistId) {
        Artist artist = ARTISTS.get(artistId);
        if (artist == null) {
            throw new ArtistNotFoundException(artistId);
        }
        return artist;
    }

    @Override
    public List<Artist> getAllArtists() {
        return List.copyOf(ARTISTS.values());
    }
}
