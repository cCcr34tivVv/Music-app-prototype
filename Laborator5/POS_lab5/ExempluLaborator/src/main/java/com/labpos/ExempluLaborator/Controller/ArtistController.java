package com.labpos.ExempluLaborator.Controller;

import com.labpos.ExempluLaborator.Dto.ArtistDto;
import com.labpos.ExempluLaborator.Dto.ContentDto;
import com.labpos.ExempluLaborator.Entity.Artist;
import com.labpos.ExempluLaborator.Entity.Content;
import com.labpos.ExempluLaborator.Service.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/songcollection/artists")
public class ArtistController {

    @Autowired
    private IArtistService artistService;

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Artist> getArtistByUuid(@PathVariable UUID uuid){
        try{
            Artist response = artistService.getArtistByUuid(uuid.toString());

            return ResponseEntity.ok(response);
        }
        catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Void> addArtist(@RequestBody ArtistDto artistDto){
        try{
            artistService.addArtist(artistDto);
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/{uuid}/songs", method = RequestMethod.PUT)
    public ResponseEntity<Void> addContent(@RequestBody ContentDto contentDto, @PathVariable UUID uuid){
        try {
            artistService.addContent(contentDto, uuid.toString());
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Artist>> getArtistByNamePartial(@RequestParam Optional<String>name, @RequestParam Optional<String>match){
        String name_artist = name.orElse("");
        String match_artist = match.orElse("");

        List<Artist> artists = artistService.getAllArtists(name_artist,match_artist);

        if(artists != null)
        {
            return ResponseEntity.ok(artists);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{uuid}/", method = RequestMethod.POST)
    public ResponseEntity<Artist> updateArtist(@PathVariable UUID uuid, @RequestParam Optional<String>name, @RequestParam Optional<Boolean>active){
        String name_artist = name.orElse("");

        Artist artist = artistService.updateArtist(uuid.toString(), name_artist, active);

        if(artist != null)
        {
            return ResponseEntity.ok(artist);
        }
        return ResponseEntity.notFound().build();
    }
}
