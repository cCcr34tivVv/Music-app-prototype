package com.labpos.ExempluLaborator.Service;

import com.labpos.ExempluLaborator.Dto.ArtistDto;
import com.labpos.ExempluLaborator.Dto.ContentDto;
import com.labpos.ExempluLaborator.Entity.Artist;
import com.labpos.ExempluLaborator.Entity.Content;
import com.labpos.ExempluLaborator.Repository.IArtistRepository;
import com.labpos.ExempluLaborator.Repository.IContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService implements IArtistService {

    @Autowired
    private IArtistRepository artistRepository;

    @Autowired
    private IContentRepository contentRepository;

    @Override
    public Artist getArtistByUuid(String uuid) throws Exception {
        List<Artist> artist = artistRepository.getByUuid(uuid);
        if (artist.isEmpty())
            throw new Exception();
        return artist.get(0);
    }

    @Override
    public void addArtist(ArtistDto artistDto) {
        Artist artist = new Artist(UUID.randomUUID().toString(), artistDto.getName(), artistDto.isActive());
        artistRepository.save(artist);
    }

    @Override
    public void addContent(ContentDto contentDto, String uuid) {
        try{
            Content content = new Content(0,contentDto.getName(), contentDto.getGenre(), contentDto.getYear(), contentDto.getType());
            contentRepository.save(content);
            Artist artist = artistRepository.getByUuid(uuid).get(0);
            Content good_content = contentRepository.getByAll(content.getName(), content.getGenre(), content.getYear(), content.getType()).get(0);
            artist.addToList(good_content);
            contentRepository.save(good_content);
        } catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public List<Artist> getAllArtists(String name, String match) {
        List<Artist> artists = null;

        if(name.compareTo("") != 0){
            //trebuie sa filtrez dupa nume
            if (match.compareTo("") == 0) {
                artists = artistRepository.getByNamePartial(name);
            }
            else {
                artists = artistRepository.getByNameStrict(name);
            }
        }
        else{
            artists = artistRepository.getAll();
        }

        return artists;
    }

    @Override
    public Artist updateArtist(String uuid, String name, Optional<Boolean> active) {
        if(name.compareTo("") != 0){
            artistRepository.updateName(name, uuid);
        }

        if(!active.isEmpty()) {
            boolean active_ceva = active.orElse(false);
            
            artistRepository.updateActive(active_ceva, uuid);
        }

        Artist  artist = artistRepository.getByUuid(uuid).get(0);

        return artist;
    }
}