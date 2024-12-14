package com.example.JPAPractice.demo.Repository;

import com.example.JPAPractice.demo.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
