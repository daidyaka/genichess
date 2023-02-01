package com.trufanov.genichess.dto;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == PieceColor.WHITE ? "♗" : "♝";
    }

}
