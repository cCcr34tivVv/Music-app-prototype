package com.labpos.ExempluLaborator.Service;

import com.labpos.ExempluLaborator.Dto.ArtistDto;
import com.labpos.ExempluLaborator.Dto.ContentDto;
import com.labpos.ExempluLaborator.Entity.Artist;
import com.labpos.ExempluLaborator.Entity.Content;

import java.util.List;
import java.util.Optional;

public interface IArtistService {

    Artist getArtistByUuid(String uuid) throws Exception;

//    Artist getArtistByName(String name);
//
//    Artist getArtistByActive(boolean active);

    void addArtist(ArtistDto artistDto);

    void addContent(ContentDto contentDto, String uuid);

    List<Artist> getAllArtists(String name, String match);

    Artist updateArtist(String uuid, String name,Optional<Boolean> active);
}
