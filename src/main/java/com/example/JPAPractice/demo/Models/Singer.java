package com.example.JPAPractice.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Singer")
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDate bornDate;


    @ManyToMany(mappedBy = "singers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs;

    public Singer(String name, LocalDate bornDate) {
        this.name = name;
        this.bornDate = bornDate;
    }

    public Singer(){}



    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }



    @Override
    public String toString() {
        return "ID: " + id +
                " Name: '" + name + '\'' +
                " Born Date: " + bornDate +
                " Songs: " + (songs != null ? songs.stream().map(Song::getName).toList() : "[]");
    }

}
