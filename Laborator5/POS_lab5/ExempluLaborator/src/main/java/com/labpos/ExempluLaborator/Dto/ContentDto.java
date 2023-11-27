package com.labpos.ExempluLaborator.Dto;

public class ContentDto {
    private String name;
    private String genre;
    private int year;
    private String type;

    public ContentDto(String name, String genre, int year, String type) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
