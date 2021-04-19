/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.board;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.etc.Move;
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
    private LinkedList<Piece>[] piecesOnBoard = new LinkedList[2];//piecesOnBoard[0] well hold a list of whites pieces and piecesOnBoard[1] well hold a list of blacks pieces

    public Board(Board b){
        
    }
    
    public Board(boolean initialize) {
        if(initialize){
            initializeBoard();
            initializeListsOfLocations();
        }
    }
    
    public void initializeBoard(){
        
        initializeTiles();
        
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
        
        initializeOccupied();
        
        initLocations();
    }
    
    public void initializeTiles(){
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
                    piecesOnBoard[0].add(board[i][j].getPiece());
                }
            }
        }
        
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j].getPiece();
                if(p.getColor() == Colors.Black){
                    piecesOnBoard[1].add(board[i][j].getPiece());
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
        getMovesForAllPiecesOfSpecificColorByStep(color);
        removeIllegalMovesByChecks(color);
        addCastlingToListOfPossibleMoves(color);
    }
    
    /**
     *this function will call the function "make list" for all the pieces of a specific color
     * and add to the list of moves all possible moves by the movement of the pieces without
     * considering checks
     * @param color
     */
    public void getMovesForAllPiecesOfSpecificColorByStep(Colors color){
        int wb = (color == Colors.White) ? 0 : 1 ;// wb = white or black
        for (int i = 0; i < piecesOnBoard[wb].size(); i++) {
            piecesOnBoard[wb].get(i).makeList(board);
        }
    }
    
    /**
     *this function well go over the list of moves of all the pieces of a specific color
     * and remove destinations that make the players king be in check
     * @param color
     */
    public void removeIllegalMovesByChecks(Colors color){
        int wb = (color == Colors.White) ? 0 : 1 ;// wb = white or black
        for (int i = 0; i < piecesOnBoard[wb].size(); i++) {
            Piece p = piecesOnBoard[wb].get(i);
            for (int j = 0; j < p.getList().size(); j++) {
                if (movePutsPlayrInCheck(p.getList().get(j))) {
                    p.getList().remove(j);
                    j--;
                }
            }
        }
    }
    
    public boolean movePutsPlayrInCheck(Move m){
        Board copy = cloneBoardForTestingPurposes();
        copy.executeMovePartially(m);
        
        LocationIn2DArray l = m.getSourceAndDestination().getSource();
        Piece p = board[l.getRow()][l.getColumn()].getPiece();
        Colors myColor = p.getColor();
        Colors enemyColor = reverseColor(myColor);
        copy.getMovesForAllPiecesOfSpecificColorByStep(enemyColor);
        
        return copy.kingIsUnderAttack(myColor);
    }
    
    private Board cloneBoardForTestingPurposes(){
        Board toReturn = new Board(false);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                toReturn.board[i][j].setPiece(this.board[i][j].getPiece().clone());
            }
        }
        toReturn.piecesOnBoard[0] = (LinkedList<Piece>) this.piecesOnBoard[0].clone();
        toReturn.piecesOnBoard[1] = (LinkedList<Piece>) this.piecesOnBoard[1].clone();
        return toReturn;
    }
    
    private void executeMovePartially(Move m){
        removePieceFromBoardForTestingPurposes(m.getSourceAndDestination().getDestination());
        changeLocationOfPieceForTestingPurposes(m);
    }
    
    private void removePieceFromBoardForTestingPurposes(LocationIn2DArray l){
        Tile t = board[l.getRow()][l.getColumn()];
        if (t.isOccupied()) {
            int i = (t.getPiece().getColor() == Colors.White) ? 0 : 1 ;
            piecesOnBoard[i].remove(t.getPiece());
            t.setPiece(null);
        }
    }
    
    private void changeLocationOfPieceForTestingPurposes(Move m){
        LocationIn2DArray source = m.getSourceAndDestination().getSource();
        LocationIn2DArray destination = m.getSourceAndDestination().getDestination();
        Tile sourceTile = board[source.getRow()][source.getColumn()];
        Tile destinationTile = board[destination.getRow()][destination.getColumn()];
        destinationTile.setPiece(sourceTile.getPiece());
        sourceTile.setPiece(null);
    }
    
    private Colors reverseColor(Colors c){
        return (c == Colors.White) ? Colors.Black : Colors.White;
    }
    
    public boolean kingIsUnderAttack(Colors c){
        int wb = (c == Colors.White) ? 0 : 1 ;
        LocationIn2DArray kingsLocation = null;
        for (int i = 0; i < piecesOnBoard[wb].size(); i++) {
            Piece p = piecesOnBoard[wb].get(i);
            if (p.getClass() == King.class) {
                kingsLocation = p.getLocation();
            }
        }
        wb = (wb == 0) ? 1 : 0 ;
        for (int i = 0; i < piecesOnBoard[wb].size(); i++) {
            Piece p = piecesOnBoard[wb].get(i);
            for (int j = 0; j < p.getList().size(); j++) {
                Move m = p.getList().get(j);
                if (m.getSourceAndDestination().getDestination().equals(kingsLocation)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void addCastlingToListOfPossibleMoves(Colors color){
        
    }
    
    public void print(){
        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
        }
        System.out.println("");
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
            for (int j = 0; j < 8; j++) {
                Tile t = board[i][j];
                String s = t.isOccupied() ? t.getPiece().toString() : " " ;
                System.out.printf("%s", s);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public LinkedList<Piece>[] getPiecesOnBoard() {
        return piecesOnBoard;
    }

    public void setPiecesOnBoard(LinkedList<Piece>[] piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }
}
