package com.trufanov.genichess;

import com.trufanov.genichess.dto.Bishop;
import com.trufanov.genichess.dto.King;
import com.trufanov.genichess.dto.Knight;
import com.trufanov.genichess.dto.Pawn;
import com.trufanov.genichess.dto.Piece;
import com.trufanov.genichess.dto.Point;
import com.trufanov.genichess.dto.Queen;
import com.trufanov.genichess.dto.Rook;

public final class Chessboard {

    private static final int BOARD_SIZE = 8;

    private static Chessboard INSTANCE = null;
    private final Piece[][] board;

    private Chessboard() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        setUpBoardPieces();
    }

    public static Chessboard getBoard() {
        if (INSTANCE == null) {
            INSTANCE = new Chessboard();
        }
        return INSTANCE;
    }

    private void setUpBoardPieces() {
        //set pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(Piece.PieceColor.BLACK);
            board[BOARD_SIZE - 2][i] = new Pawn(Piece.PieceColor.WHITE);
        }

        //set rooks
        board[0][0] = new Rook(Piece.PieceColor.BLACK);
        board[0][7] = new Rook(Piece.PieceColor.BLACK);
        board[7][0] = new Rook(Piece.PieceColor.WHITE);
        board[7][7] = new Rook(Piece.PieceColor.WHITE);

        //set knights
        board[0][1] = new Knight(Piece.PieceColor.BLACK);
        board[0][6] = new Knight(Piece.PieceColor.BLACK);
        board[7][1] = new Knight(Piece.PieceColor.WHITE);
        board[7][6] = new Knight(Piece.PieceColor.WHITE);

        //set bishops
        board[0][2] = new Bishop(Piece.PieceColor.BLACK);
        board[0][5] = new Bishop(Piece.PieceColor.BLACK);
        board[7][2] = new Bishop(Piece.PieceColor.WHITE);
        board[7][5] = new Bishop(Piece.PieceColor.WHITE);

        //set queens
        board[0][3] = new Queen(Piece.PieceColor.BLACK);
        board[7][3] = new Queen(Piece.PieceColor.WHITE);

        //set kings
        board[0][4] = new King(Piece.PieceColor.BLACK);
        board[7][4] = new King(Piece.PieceColor.WHITE);
    }

    public void move(Point from, Point to) {
        if (!isValidPoint(from) || !isValidPoint(to) && (from.x != to.x && from.y != to.y)) {
            System.out.println("PASHEL NAXUI");
            return;
        }

        Piece pieceFrom = board[from.x][from.y];
        boolean isPieceToPresent = board[to.x][to.y] != null;

        if (pieceFrom instanceof Pawn) {
            if (from.x == to.x) { //moves forward
                Piece.PieceColor pieceColor = pieceFrom.getColor();



                if (Piece.PieceColor.WHITE == pieceColor && to.y - from.y > 1) {
                    throw new RuntimeException("PASHEL NAXUI");
                }

                if (Piece.PieceColor.BLACK == pieceColor && from.y - to.y > 1) {
                    throw new RuntimeException("PASHEL NAXUI");
                }


            } else if (Math.abs(from.x - to.x) == 1) {

            } else {
                throw new RuntimeException("PASHEL NAXUI");
            }
        } else if (pieceFrom instanceof Bishop) {

        } else if (pieceFrom instanceof King) {

        } else if (pieceFrom instanceof Queen) {

        } else if (pieceFrom instanceof Knight) {

        } else if (pieceFrom instanceof Rook) {

        }
    }

    private boolean isValidPoint(Point point) {
        return point.x >= 0 && point.x <= 7 && point.y >= 0 && point.y <= 7;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                sb.append(piece == null ? " " : piece.toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
