/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.board;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.pieces.Bishop;
import gamemecanics.pieces.King;
import gamemecanics.pieces.Knight;
import gamemecanics.pieces.Pawn;
import gamemecanics.pieces.Piece;
import gamemecanics.pieces.Queen;
import gamemecanics.pieces.Rook;
import java.util.LinkedList;

/**
 *
 * @author elie
 */
public class Board {
    private Tile[][] board = new Tile[8][8];
    private LinkedList<LocationIn2DArray> whitePiecesLocations;
    private LinkedList<LocationIn2DArray> blackPiecesLocations;
    private LinkedList<LocationIn2DArray>[] piecesOnBoard = new LinkedList[2];//piecesOnBoard[0] well hold a list of whites pieces and piecesOnBoard[1] well hold a list of blacks pieces

    public Board() {
        initializeBoard();
        initializeListsOfLocations();
    }
    
    public void initializeBoard(){
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
        
        initializeColorOfTiles();
        
        initializeOccupied();
        
        initLocations();
    }
    
    public void initializeColorOfTiles(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile((i + j) % 2 == 0 ? Tile.Color.white : Tile.Color.black);
            }
        }
    }
    
    public void initializeOccupied(){
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
    }
    
    public void initLocations(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].getPiece().getLocation().setLocationOfRowAndColumn(i, j);
            }
        }
        
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].getPiece().getLocation().setLocationOfRowAndColumn(i, j);
            }
        }
    }
    
    public void initializeListsOfLocations(){
        piecesOnBoard[0] = new LinkedList<>();
        piecesOnBoard[1] = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j].getPiece();
                if(p.getColor() == Colors.White){
                    piecesOnBoard[0].add(board[i][j].getPiece().getLocation());
                }
            }
        }
        
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j].getPiece();
                if(p.getColor() == Colors.Black){
                    piecesOnBoard[1].add(board[i][j].getPiece().getLocation());
                }
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }
    
    public void getMoves(Colors color){
        getMovesForAllPiecesOfSpecificColorStep1(color);
        getMovesForAllPiecesOfSpecificColorStep2(color);
        addCastlingToListOfPossibleMoves(color);
    }
    
    /**
     *this function will call the function "make list" for all the pieces of a specific color
     * and add to the list of moves all possible moves by the movement of the pieces without
     * considering checks
     * @param color
     */
    public void getMovesForAllPiecesOfSpecificColorStep1(Colors color){
        if (color == Colors.White) {
            getMovesForAllPiecesInLocationsInList(whitePiecesLocations);
        } else {
            getMovesForAllPiecesInLocationsInList(blackPiecesLocations);
        }
    }
    
    public void getMovesForAllPiecesInLocationsInList(LinkedList<LocationIn2DArray> list){
        for (int i = 0; i < list.size(); i++) {
            LocationIn2DArray l = list.get(i);
            board[l.getColumn()][l.getColumn()].getPiece().makeList(board);
        }
    }
    
    /**
     *this function well go over the list of moves of all the pieces of a specific color
     * and remove destinations that make the players king be in check
     * @param color
     */
    public void getMovesForAllPiecesOfSpecificColorStep2(Colors color){
        
    }
    
    public void addCastlingToListOfPossibleMoves(Colors color){
        
    }
}
