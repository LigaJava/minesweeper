package com.example.minesweeper;

import com.example.minesweeper.data.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinesweeperApplication {

	public static Game game;

	public static void main(String[] args) {

		SpringApplication.run(MinesweeperApplication.class, args);
	}
}
