package com.trufanov.genichess.dto;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == PieceColor.WHITE ? "♘" : "♞";
    }

}
