package com.trufanov.genichess.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Piece {

    public final PieceColor color;

    public enum PieceColor {
        WHITE, BLACK
    }

}
