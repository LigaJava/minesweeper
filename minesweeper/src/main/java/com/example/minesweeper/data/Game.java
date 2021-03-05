package com.example.minesweeper.data;

import lombok.Getter;

public class Game {
    private Board board;
@Getter
    private final int size;

    public Game(int size) { // game constructor
        board = new Board(size); // size initialization
        this.size = size;
        start();// calls "start" in a background
    }

    public void start() {// public - will call externally
        board.initBoard();// calling initBoard
    }

    public GameResult checkField(int row, int column) {// calls externally
        var result = board.checkField(row, column);

        if (!result) { //if lost
            return GameResult.LOST;
        }

        if (board.isOpen()) {
            return GameResult.WON;
        }
        return GameResult.CONTINUE;
    }

    public int getFieldValue(int row, int column) {
        return board.getFieldInfo(row, column);

    }
}

