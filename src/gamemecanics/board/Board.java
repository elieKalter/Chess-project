/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.board;

import fbg.Colors;
import gamemecanics.pieces.Bishop;
import gamemecanics.pieces.King;
import gamemecanics.pieces.Knight;
import gamemecanics.pieces.Pawn;
import gamemecanics.pieces.Queen;
import gamemecanics.pieces.Rook;

/**
 *
 * @author elie
 */
public class Board {
    private Tile[][] board = new Tile[8][8];

    public Board() {
        //initialize color
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile((i + j) % 2 == 0 ? Tile.Color.black : Tile.Color.white);
            }
        }
        
        //initialize occupied
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setOccupied(true);
            }
        }
        
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setOccupied(true);
            }
        }
        
        //initialize piece
        //black
        board[0][0].setPiece(new Rook(Colors.Black));
        board[0][1].setPiece(new Knight(Colors.Black));
        board[0][2].setPiece(new Bishop(Colors.Black));
        board[0][3].setPiece(new Queen(Colors.Black));
        board[0][4].setPiece(new King(Colors.Black));
        board[0][5].setPiece(new Bishop(Colors.Black));
        board[0][6].setPiece(new Knight(Colors.Black));
        board[0][7].setPiece(new Bishop(Colors.Black));
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(Colors.Black));
        }
        
        //white
        board[7][0].setPiece(new Rook(Colors.White));
        board[7][1].setPiece(new Knight(Colors.White));
        board[7][2].setPiece(new Bishop(Colors.White));
        board[7][3].setPiece(new Queen(Colors.White));
        board[7][4].setPiece(new King(Colors.White));
        board[7][5].setPiece(new Bishop(Colors.White));
        board[7][6].setPiece(new Knight(Colors.White));
        board[7][7].setPiece(new Bishop(Colors.White));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(Colors.White));
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }
    
    
}
