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
import gamemecanics.etc.Functions;
import gamemecanics.etc.MoveSourceDestination;


/**
 *
 * @author elie
 */
public abstract class Piece {
    final static Functions f = new Functions();
    protected Colors color;
    protected boolean moved;
    protected int value;
    protected ListOfMoves list;
    protected LocationIn2DArray location;

    public Piece() {
        this.moved = false;
        this.location = new LocationIn2DArray();
        this.list = new ListOfMoves();
    }
    
    public Piece(Colors color, int value) {
        this.color = color;
        this.moved = false;
        this.value = value;
        this.location = new LocationIn2DArray();
        this.list = new ListOfMoves();
    }

    public Piece(Colors color, boolean moved, int value, ListOfMoves list, LocationIn2DArray location) {
        this.color = color;
        this.moved = moved;
        this.value = value;
        this.list = list;
        this.location = location;
    }
    
    abstract public void makeList(Board b);
    abstract public Piece clone();
    
    protected void addMove(LocationIn2DArray l){
        MoveSourceDestination m = new MoveSourceDestination(l, this.location);
        this.list.getListOfMoves().add(m);
    }
}


