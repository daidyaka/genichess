package com.trufanov.genichess;

import com.trufanov.genichess.dto.Point;

public class Application {

    public static void main(String[] args) {
        Chessboard board = Chessboard.getBoard();
        System.out.println(board);
        System.out.println("===================");

        board.move(Point.of(6, 0), Point.of(4, 0));
        //TODO: check why white pawns can move to the side
        System.out.println(board);
    }

}
