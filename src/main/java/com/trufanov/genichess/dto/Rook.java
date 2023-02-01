package com.trufanov.genichess.dto;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == PieceColor.WHITE ? "♖" : "♜";
    }

}
