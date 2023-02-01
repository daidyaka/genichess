package com.trufanov.genichess.dto;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public String toString() {
        return this.color == PieceColor.WHITE ? "♔" : "♚";
    }

}
