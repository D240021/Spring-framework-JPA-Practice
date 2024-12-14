package com.example.JPAPractice.demo.Main;

import com.example.JPAPractice.demo.Enums.Genre;
import com.example.JPAPractice.demo.Exceptions.NoSingerFoundException;
import com.example.JPAPractice.demo.Exceptions.NoSongFoundException;
import com.example.JPAPractice.demo.Models.Singer;
import com.example.JPAPractice.demo.Models.Song;
import com.example.JPAPractice.demo.Repository.SingerRepository;
import com.example.JPAPractice.demo.Repository.SongRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner;
    private final SingerRepository singerRepository;
    private final SongRepository songRepository;

    public Main(SingerRepository singerRepository, SongRepository songRepository) {
        this.songRepository = songRepository;
        this.scanner = new Scanner(System.in);
        this.singerRepository = singerRepository;
    }

    public void showMainMenu(){

        while (true) {

            var menu = "Welcome, user!\n" +
                    "Please, choose an option below\n" +
                    "1.Add a new singer\n" +
                    "2.Get all singers\n" +
                    "3.Delete singer by ID\n" +
                    "4.Delete singer by name\n" +
                    "5.Update singer by name\n" +
                    "6.Update singer by born date\n" +
                    "7.Get all songs\n" +
                    "8.Add a new song\n" +
                    "9.Add song to singer\n" +
                    "10.Get songs by singer ID\n" +
                    "0.Exit\n";

            System.out.println(menu);

            int option = this.scanner.nextInt();

            this.processOptions(option);

            if(option == 0)
                break;
        }


    }

    private void showGenreMenu(){

        String menu = "Select the song's genre:\n" +
                "1.SALSA\n" +
                "2.ROCK\n" +
                "3.BACHATA\n" +
                "4.POP\n" +
                "5.RAP";

        System.out.println(menu);

    }

    private void processOptions(int option){

        switch (option){
            case 1 :
                addNewSinger();
                break;
            case 2 :
                getAllSingers();
                break;
            case 3 :
                deleteSingerByID();
                break;
            case 4 :
                deleteSingerByName();
                break;
            case 5 :
                updateSingerByName();
                break;
            case 6 :
                updateSingerByBirdDate();
                break;
            case 7 :
                getAllSongs();
                break;
            case 8 :
                addNewSong();
                break;
            case 9 :
                addSongToSinger();
                break;
            case 10 :
                getSongsBySingerID();
                break;
        }
    }

    private Genre processGenreOptions(int option){

        switch (option) {
            case 1:
                return Genre.SALSA;
            case 2:
                return Genre.ROCK;
            case 3:
                return Genre.BACHATA;
            case 4:
                return Genre.POP;
            case 5:
                return Genre.RAP;
            default:
                return null;
        }

    }

    private void addNewSinger(){

        System.out.println("Insert singer's name:");

        String singerName = this.scanner.next();

        System.out.println("Insert singer's born date:");

        LocalDate bornDate = LocalDate.parse( this.scanner.next() );


        Singer newSinger = new Singer(singerName, bornDate);

        this.singerRepository.save(newSinger);

    }

    private void getAllSingers() {
        List<Singer> singers = this.singerRepository.findAll();

        if (!singers.isEmpty()) {
            singers.forEach(System.out::println);
            return;
        }

        System.out.println("There are no singers registered.\n");
    }

    private void deleteSingerByID(){

        System.out.println("Insert singer's ID");

        int id = this.scanner.nextInt();

        this.singerRepository.deleteById(id);

    }

    private void deleteSingerByName(){

        System.out.println("Insert singer's name:");

        String singerName = this.scanner.next();

        this.singerRepository.deleteByNameContainsIgnoreCase(singerName);
    }

    private void updateSingerByName(){

        System.out.println("Insert singer's ID to update");

        int idSinger = this.scanner.nextInt();


        System.out.println("Insert new singer's name:");

        String singerName = this.scanner.next();

        this.singerRepository.updateSingerByName(idSinger, singerName);
    }

    private void updateSingerByBirdDate(){

        System.out.println("Insert singer's ID to update");

        int idSinger = this.scanner.nextInt();

        System.out.println("Insert new singer's born date:");

        LocalDate singerBornDate = LocalDate.parse(this.scanner.next());

        this.singerRepository.updateSingerByBornDate(idSinger, singerBornDate);
    }

    private void getAllSongs() {
        List<Song> songs = this.songRepository.findAll();

        if (!songs.isEmpty()) {
            songs.forEach(System.out::println);
            return;
        }

        System.out.println("There are no songs registered.\n");
    }

    private void addNewSong() {

        System.out.println("Insert song's name:");

        String songName = this.scanner.next();


        this.showGenreMenu();
        int genreOption = this.scanner.nextInt();


        Genre selectedGenre = this.processGenreOptions(genreOption);

        System.out.println("Insert song's released date:");

        LocalDate releasedDate = LocalDate.parse( this.scanner.next() );

        Song newSong = new Song(songName, selectedGenre, releasedDate);

        this.songRepository.save(newSong);

    }

    private void addSongToSinger(){

        try {

            System.out.println("Insert singer's ID:");
            int idSinger = this.scanner.nextInt();

            System.out.println("Insert song's ID:");
            int idSong = this.scanner.nextInt();

            Singer singer = this.singerRepository.findById(idSinger).orElseThrow( () -> new NoSingerFoundException());
            Song song = this.songRepository.findById(idSong).orElseThrow(() -> new NoSongFoundException());

            singer.getSongs().add(song);

            song.getSingers().add(singer);

            this.singerRepository.save(singer);

        } catch (NoSingerFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSongFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void getSongsBySingerID() {

        System.out.println("Insert singer's ID:");
        int idSinger = this.scanner.nextInt();

        try {
            Singer singer = this.singerRepository.findById(idSinger).orElseThrow( () -> new NoSingerFoundException());
            System.out.println("Song's names:\n");

            singer.getSongs().stream()
                    .map(Song::getName)
                    .forEach(System.out::println);

        } catch (NoSingerFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
