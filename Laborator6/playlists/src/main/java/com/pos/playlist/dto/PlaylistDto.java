package com.pos.playlist.dto;

import com.pos.playlist.entity.Song;

import java.util.List;

public class PlaylistDto {
    private String title;
    private int userId;

    public PlaylistDto(String title, int userId) {
        this.title = title;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
