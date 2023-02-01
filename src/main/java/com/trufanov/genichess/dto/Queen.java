package com.trufanov.genichess.dto;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == PieceColor.WHITE ? "♕" : "♛";
    }

}
