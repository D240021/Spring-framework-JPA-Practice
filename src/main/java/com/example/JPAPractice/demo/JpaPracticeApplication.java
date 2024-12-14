package com.example.JPAPractice.demo;

import com.example.JPAPractice.demo.Main.Main;
import com.example.JPAPractice.demo.Repository.SingerRepository;
import com.example.JPAPractice.demo.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaPracticeApplication implements CommandLineRunner {

	@Autowired //Indicates dependecy injection
	private SingerRepository singerRepository;

	@Autowired
	private SongRepository songRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Main main = new Main(singerRepository, songRepository);

		main.showMainMenu();

	}
}
