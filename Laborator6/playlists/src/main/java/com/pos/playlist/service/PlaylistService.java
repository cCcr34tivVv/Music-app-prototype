package com.pos.playlist.service;

import com.pos.playlist.dto.PlaylistDto;
import com.pos.playlist.entity.Playlist;
import com.pos.playlist.entity.Song;
import com.pos.playlist.exception.UserDoesntExists;
import com.pos.playlist.repository.IPlaylistRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;


@Service
public class PlaylistService implements IPlaylistService{

    @Autowired
    private IPlaylistRepository playlistRepository;

    @Override
    public void addPlaylist(PlaylistDto playlistDto) {
        Playlist playlist = new Playlist(new ObjectId().toString(),playlistDto.getTitle(), new Vector<Song>(), playlistDto.getUserId());
        playlistRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(String playlistId, int userId) throws UserDoesntExists {
        Playlist playlist = playlistRepository.findById(playlistId).get();
        if(userId != playlist.getUserId())
            throw new UserDoesntExists("User doesn't exists!");
        playlistRepository.delete(playlist);
    }

    @Override
    public void addSongToPlaylist(Song song, String playlistId, int userId) throws UserDoesntExists {
        Playlist playlist = playlistRepository.findById(playlistId).get();
        if(userId != playlist.getUserId())
            throw new UserDoesntExists("User doesn't exists!");
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    @Override
    public void deleteSongFromPlaylist(Song song, String playlistId, int userId) throws UserDoesntExists {
        Playlist playlist = playlistRepository.findById(playlistId).get();
        if(userId != playlist.getUserId())
            throw new UserDoesntExists("User doesn't exists!");
        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylistFromUser(int userId) {
        return playlistRepository.findAllByUserId(userId);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll().stream().toList();
    }
}
