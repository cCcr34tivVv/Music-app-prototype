package com.pos.playlist.repository;

import com.pos.playlist.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findAllByUserId (int userId);
}
