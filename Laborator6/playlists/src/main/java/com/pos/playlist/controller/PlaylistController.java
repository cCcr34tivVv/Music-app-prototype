package com.pos.playlist.controller;

import com.pos.playlist.dto.PlaylistDto;
import com.pos.playlist.entity.Playlist;
import com.pos.playlist.entity.Song;
import com.pos.playlist.exception.UserDoesntExists;
import com.pos.playlist.service.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Void> addPlaylist (@RequestBody PlaylistDto playlistDto){
        try {
            playlistService.addPlaylist(playlistDto);
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlaylist (@PathVariable String id, @RequestParam int userId){
        try{
            playlistService.deletePlaylist(id, userId);
            return ResponseEntity.ok().build();
        }catch (UserDoesntExists userDoesntExists){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/{id}/song", method = RequestMethod.POST)
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable String id, @RequestParam int userId, @RequestBody Song song){
        try{
            playlistService.addSongToPlaylist(song, id, userId);
            return ResponseEntity.ok().build();
        }catch (UserDoesntExists userDoesntExists){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/{id}/song", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSongFromPlaylist(@PathVariable String id, @RequestParam int userId, @RequestBody Song song){
        try{
            playlistService.deleteSongFromPlaylist(song, id, userId);
            return ResponseEntity.ok().build();
        }catch (UserDoesntExists userDoesntExists){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Playlist>> getAllPlaylistFromUser(@PathVariable int userId){
        try{
            List<Playlist> playlists = playlistService.getAllPlaylistFromUser(userId);
            return ResponseEntity.ok(playlists);
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Playlist>> getAllPlaylists(){
        try{
            List<Playlist> playlists = playlistService.getAllPlaylists();
            return ResponseEntity.ok(playlists);
        }catch (Exception exception){
            return ResponseEntity.internalServerError().build();
        }
    }
}
