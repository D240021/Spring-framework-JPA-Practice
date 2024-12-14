package com.example.JPAPractice.demo.Repository;

import com.example.JPAPractice.demo.Models.Singer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SingerRepository extends JpaRepository<Singer,Integer> {

    @Transactional //It's necessary to execute writing operations
    public void deleteByNameContainsIgnoreCase(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Singer s SET s.name = :name WHERE s.id = :id") //JPQL Query
    public void updateSingerByName(int id, String name);

    @Modifying
    @Transactional
    @Query("UPDATE Singer s SET s.bornDate = :bornDate WHERE s.id = :id") //JPQL Query
    public void updateSingerByBornDate(int id, LocalDate bornDate);


}
