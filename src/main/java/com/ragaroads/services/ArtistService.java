package com.ragaroads.services;

import com.ragaroads.models.Artist;

import java.util.List;

public interface ArtistService {
    Artist getArtistById(Long artistId);
    List<Artist> getAllArtists();
}
