/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.pieces;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.board.Board;
import gamemecanics.etc.ListOfMoves;

/**
 *
 * @author elie
 */
public class Pawn extends Piece{
    boolean wjmtft; //wjmtft = was just moved the first time

    public Pawn() {
        super();
        wjmtft = false;
    }
    
    public Pawn(Colors color) {
        super(color, 1);
        wjmtft = false;
    }
    
    public Pawn(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location){
        this(color, moved, list, location, false);
    }
    
    public Pawn(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location, boolean wjmtft){
        super(color, moved, 1, list, location);
        this.wjmtft = wjmtft;
    }
    
    public Pawn(Pawn p) {
        this.color = p.color;
        this.list = p.list;
        this.location = p.location;
        this.moved = p.moved;
        this.value = p.value;
        this.wjmtft = p.wjmtft;
    }
    
    @Override
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        LocationIn2DArray tmp2; // well hold places to check some things about when checking if to move to a place held in tmp
        int tb = (this.color == Colors.White)? 1 : -1; //this var well determine if forward is towords the top or bottom
        
        //1 and 2 forward
        tmp = this.location.add(tb, 0); // 1 forward
        if (f.validLocation(tmp) && !b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()) {
            addMove(tmp);
            tmp.add(tb, 0); // 2 forward
            if (f.validLocation(tmp) && !this.moved && 
                    !b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()) {
                addMove(tmp);
            }
        }
        
        //diagonal 1
        int direction = 1;
        tmp = this.location.add(tb, direction);
        if (f.validLocation(tmp)) {
            if (b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()
                    && b.getBoard()[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color) {
                addMove(tmp);
            }
            //en passant
            tmp2 = location.add(0, direction);
            Piece piece = b.getBoard()[tmp2.getRow()][tmp2.getColumn()].getPiece();
            if (piece.getClass() == this.getClass()) {
                Pawn p = (Pawn) piece;
                if(!b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()
                        && p.color != this.color
                        && p.wjmtft){
                    addMove(tmp);
                }
            }
        }
        
        //diagonal 2
        direction = -1;
        tmp = this.location.add(tb, direction);
        if (f.validLocation(tmp)) {
            if (b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()
                    && b.getBoard()[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color) {
                addMove(tmp);
            }
            //en passant
            tmp2 = location.add(0, direction);
            Piece piece = b.getBoard()[tmp2.getRow()][tmp2.getColumn()].getPiece();
            if (piece.getClass() == this.getClass()) {
                Pawn p = (Pawn) piece;
                if(!b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()
                        && p.color != this.color
                        && p.wjmtft){
                    addMove(tmp);
                }
            }
        }
    }

    @Override
    public Pawn clone() {
        return new Pawn(this.color, this.moved, this.list, this.location);
    }
}
