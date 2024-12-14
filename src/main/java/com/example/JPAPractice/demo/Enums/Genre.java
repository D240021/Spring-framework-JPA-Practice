package com.example.JPAPractice.demo.Enums;

public enum Genre {

    SALSA("SALSA"),

    ROCK("ROCK"),

    BACHATA("BACHATA"),

    POP("POP"),

    RAP("RAP");

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
