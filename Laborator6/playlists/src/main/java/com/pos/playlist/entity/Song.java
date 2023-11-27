package com.pos.playlist.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Song {
    private int id;
    private String title;
    private String href;
}
