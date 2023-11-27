package com.pos.playlist.service;

import com.pos.playlist.dto.PlaylistDto;
import com.pos.playlist.entity.Playlist;
import com.pos.playlist.entity.Song;
import com.pos.playlist.exception.UserDoesntExists;

import java.util.List;

public interface IPlaylistService {

    public void addPlaylist(PlaylistDto playlistDto);

    public void deletePlaylist(String playlistId, int userId) throws UserDoesntExists;

    public void addSongToPlaylist(Song song, String playlistId, int userId) throws UserDoesntExists;

    public void deleteSongFromPlaylist(Song song, String playlistId, int userId) throws UserDoesntExists;

    public List<Playlist> getAllPlaylistFromUser(int userId) ;

    public List<Playlist> getAllPlaylists();
}
