package com.ragaroads.services;

import com.ragaroads.exceptions.ConcertNotFoundException;
import com.ragaroads.models.Concert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConcertServiceImpl implements ConcertService {

    private static final Map<Long, Concert> CONCERTS = Map.ofEntries(
        Map.entry(1L,  new Concert(1L,  "Corporate Night Chennai",    "2026-08-01", "18:00", "Chennai",        "Tamil Nadu",      "BALANCED",     "Singer-Male",   15000.0)),
        Map.entry(2L,  new Concert(2L,  "Wedding Reception Chennai",  "2026-08-05", "19:00", "Chennai",        "Tamil Nadu",      "AVAILABILITY", "Singer-Female", 12000.0)),
        Map.entry(3L,  new Concert(3L,  "Infosys Annual Day",         "2026-08-08", "18:30", "Bangalore",      "Karnataka",       "BALANCED",     "Guitarist",     10000.0)),
        Map.entry(4L,  new Concert(4L,  "Tech Summit",                "2026-08-10", "17:00", "Bangalore",      "Karnataka",       "AVAILABILITY", "Drummer",        9000.0)),
        Map.entry(5L,  new Concert(5L,  "Music Fest Hyderabad",       "2026-08-12", "18:00", "Hyderabad",      "Telangana",       "BALANCED",     "Keyboardist",   11000.0)),
        Map.entry(6L,  new Concert(6L,  "Cultural Event Pune",        "2026-08-15", "18:30", "Pune",           "Maharashtra",     "AVAILABILITY", "Flutist",       13000.0)),
        Map.entry(7L,  new Concert(7L,  "Startup Meetup Mumbai",      "2026-08-18", "19:00", "Mumbai",         "Maharashtra",     "BALANCED",     "Tablist",        8000.0)),
        Map.entry(8L,  new Concert(8L,  "Corporate Event Delhi",      "2026-08-20", "18:00", "Delhi",          "Delhi",           "AVAILABILITY", "Singer-Male",   16000.0)),
        Map.entry(9L,  new Concert(9L,  "Leadership Summit Kochi",    "2026-08-22", "18:00", "Kochi",          "Kerala",          "BALANCED",     "Singer-Female", 12000.0)),
        Map.entry(10L, new Concert(10L, "Festival Mysore",            "2026-08-25", "19:00", "Mysore",         "Karnataka",       "AVAILABILITY", "Guitarist",     10000.0)),
        Map.entry(11L, new Concert(11L, "Concert Coimbatore",         "2026-08-28", "18:00", "Coimbatore",     "Tamil Nadu",      "BALANCED",     "Drummer",        9000.0)),
        Map.entry(12L, new Concert(12L, "Corporate Vizag",            "2026-09-01", "18:00", "Visakhapatnam",  "Andhra Pradesh",  "AVAILABILITY", "Keyboardist",   10000.0)),
        Map.entry(13L, new Concert(13L, "Wedding Jaipur",             "2026-09-05", "19:00", "Jaipur",         "Rajasthan",       "BALANCED",     "Flutist",       14000.0)),
        Map.entry(14L, new Concert(14L, "Festival Lucknow",           "2026-09-08", "18:00", "Lucknow",        "Uttar Pradesh",   "AVAILABILITY", "Tablist",        8000.0)),
        Map.entry(15L, new Concert(15L, "Corporate Chandigarh",       "2026-09-10", "18:30", "Chandigarh",     "Punjab",          "BALANCED",     "Singer-Male",   15000.0)),
        Map.entry(16L, new Concert(16L, "Tech Event Indore",          "2026-09-12", "18:00", "Indore",         "Madhya Pradesh",  "AVAILABILITY", "Singer-Female", 12000.0)),
        Map.entry(17L, new Concert(17L, "Public Concert Goa",         "2026-09-15", "19:00", "Panaji",         "Goa",             "BALANCED",     "Guitarist",     12000.0)),
        Map.entry(18L, new Concert(18L, "Music Night Surat",          "2026-09-18", "18:00", "Surat",          "Gujarat",         "AVAILABILITY", "Drummer",        9000.0)),
        Map.entry(19L, new Concert(19L, "Business Meet Nagpur",       "2026-09-20", "18:00", "Nagpur",         "Maharashtra",     "BALANCED",     "Keyboardist",   10000.0)),
        Map.entry(20L, new Concert(20L, "Annual Function Patna",      "2026-09-25", "18:00", "Patna",          "Bihar",           "AVAILABILITY", "Flutist",       13000.0))
    );

    @Override
    public Concert getConcertById(Long concertId) {
        Concert concert = CONCERTS.get(concertId);
        if (concert == null) {
            throw new ConcertNotFoundException(concertId);
        }
        return concert;
    }

    @Override
    public List<Concert> getAllConcerts() {
        return List.copyOf(CONCERTS.values());
    }
}
