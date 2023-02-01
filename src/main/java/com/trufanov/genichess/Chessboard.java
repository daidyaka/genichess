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
            System.out.println("Incorrect coordinates of the input points.");
            return;
        }

        Piece pieceFrom = board[from.x][from.y];

        if (pieceFrom == null) {
            throw new RuntimeException("Nothing to move.");
        }

        boolean isPieceToPresent = board[to.x][to.y] != null;

        if (pieceFrom instanceof Pawn) {
            movePawn(pieceFrom, from, to);
        } else if (pieceFrom instanceof Bishop) {
            setPiece(to, board[from.x][from.y]);
            setPiece(from, null);

        } else if (pieceFrom instanceof King) {
            setPiece(to, board[from.x][from.y]);
            setPiece(from, null);

        } else if (pieceFrom instanceof Queen) {
            setPiece(to, board[from.x][from.y]);
            setPiece(from, null);

        } else if (pieceFrom instanceof Knight) {
            setPiece(to, board[from.x][from.y]);
            setPiece(from, null);

        } else if (pieceFrom instanceof Rook) {
            setPiece(to, board[from.x][from.y]);
            setPiece(from, null);

        }
    }

    private void movePawn(Piece pieceToMove, Point from, Point to) {
        Piece.PieceColor color = pieceToMove.color;

        if (color == Piece.PieceColor.WHITE) {

            //white pawn is on start position
            int xMove = from.x - to.x;
            int yMove = Math.abs(from.y - to.y);
            if (from.x == BOARD_SIZE - 2) {

                //check if pawn is on start, so it can move on 1 or 2 squares forward
                if (xMove == 1 || xMove == 2) {

                    //if white pawn is on start and kills by diagonal
                    if (xMove == 1 && yMove == 1 && !isSlotEmpty(to) && getPiece(to).color != Piece.PieceColor.WHITE) {
                        setPiece(to, board[from.x][from.y]);
                        setPiece(from, null);
                        return;
                    }

                    //check if nothing is in front of pawn for move
                    for (int i = 1; i < xMove; i++) {
                        Point p = Point.of(from.x - i, from.y);
                        if (!isSlotEmpty(p)) {
                            throw new RuntimeException("Slot " + p + " is not empty");
                        }
                    }

                    setPiece(to, board[from.x][from.y]);
                    setPiece(from, null);
                } else {
                    throw new RuntimeException("Illegal move for pawn. Y > 2");
                }
            } else {
                if (xMove != 1) {
                    throw new RuntimeException("Illegal move for pawn. Y > 1");
                }

                //todo: add possibility to upgrade pawn if it is on the last line of the board
                //pawn moves forward
                if (yMove == 0) {
                    if (!isSlotEmpty(to)) {
                        throw new RuntimeException("Slot " + to + " is not empty");
                    }
                    setPiece(to, board[from.x][from.y]);
                    setPiece(from, null);
                } else if (yMove == 1 && getPiece(to).color != Piece.PieceColor.WHITE) {
                    setPiece(to, board[from.x][from.y]);
                    setPiece(from, null);
                } else {
                    throw new RuntimeException("Illegal move for pawn. X > 1");
                }
            }

        } else { //black

        }
    }

    private Piece getPiece(Point point) {
        //todo: add validation for coords
        return board[point.x][point.y];
    }

    private void setPiece(Point point, Piece piece) {
        //todo: add validation for coords
        board[point.x][point.y] = piece;
    }

    private boolean isValidPoint(Point point) {
        return point.x >= 0 && point.x <= 7 && point.y >= 0 && point.y <= 7;
    }

    private boolean isSlotEmpty(Point point) {
        return board[point.x][point.y] == null;
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
