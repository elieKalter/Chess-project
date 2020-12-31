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
public class Knight extends Piece{

    public Knight() {
        super();
    }
    
    public Knight(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location){
        super(color, moved, 3, list, location);
    }
    
    public Knight(Knight k){
        this.color = k.color;
        this.moved = k.moved;
        this.list = k.list;
        this.location = k.location;
        this.value = k.value;
    }

    @Override
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        
        //i = -2;
        //j = -1;
        tmp = this.location.add(-2, -1);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //j = 1;
        tmp = this.location.add(-2, 1);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //i = -1;
        //j = -2;
        tmp = this.location.add(-1, -2);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //j = 2;
        tmp = this.location.add(-1, 2);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //i = 1;
        //j = -2;
        tmp = this.location.add(1, -2);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //j = 2;
        tmp = this.location.add(1, 2);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //i = 2;
        //j = -1;
        tmp = this.location.add(2, -1);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
        
        //j = 1;
        tmp = this.location.add(2, 1);
        if(addLocation(b, tmp)){
            addMove(tmp);
        }
    }

    @Override
    public Knight clone() {
        return new Knight(this.color, this.moved, this.list, this.location);
    }
    
    private boolean addLocation(Board b, LocationIn2DArray l){
        return f.validLocation(l)
                && (!b.getBoard()[l.getRow()][l.getColumn()].isOccupied()
                || b.getBoard()[l.getRow()][l.getColumn()].getPiece().color != this.color);
    }
}
