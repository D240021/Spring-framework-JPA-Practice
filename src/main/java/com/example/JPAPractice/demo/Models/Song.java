package com.example.JPAPractice.demo.Models;

import com.example.JPAPractice.demo.Enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private LocalDate releasedDate;

   //Tabla intermedia
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_singer",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id")
    )

    private List<Singer> singers;


    public Song(){}

    public Song(String name, Genre genre, LocalDate releasedDate) {
        this.name = name;
        this.genre = genre;
        this.releasedDate = releasedDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }


    @Override
    public String toString() {
        return "ID: " + id +
                " Name: '" + name + '\'' +
                " Genre: " + genre +
                " Released Date: " + releasedDate +
                " Singers: " + (singers != null ? singers.stream().map(Singer::getName).toList() : "[]");
    }
}
