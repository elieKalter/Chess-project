/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.pieces;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.board.Tile;
import gamemecanics.etc.Functions;
import gamemecanics.etc.Move;
import gamemecanics.etc.Move.TypeOfConsequences;
import gamemecanics.etc.SourceAndDestinationLocations;
import java.util.LinkedList;


/**
 *
 * @author elie
 */
public abstract class Piece {
    final static Functions f = new Functions();
    protected Colors color;
    protected boolean moved;
    protected int value;
    protected LinkedList<Move> list;
    protected LocationIn2DArray location;

    public Piece() {
        this.moved = false;
        this.location = new LocationIn2DArray();
        this.list = new LinkedList<>();
    }
    
    public Piece(Colors color, int value) {
        this.color = color;
        this.moved = false;
        this.value = value;
        this.location = new LocationIn2DArray();
        this.list = new LinkedList<>();
    }

    public Piece(Colors color, boolean moved, int value, LinkedList<Move> list, LocationIn2DArray location) {
        this.color = color;
        this.moved = moved;
        this.value = value;
        this.list = list;
        this.location = location;
    }
    
    abstract public void makeList(Tile[][] b);
    abstract protected TypeOfConsequences calcCosequences(Tile[][] b,
                                                            SourceAndDestinationLocations sourceDes,
                                                            int tb,
                                                            String info);
    abstract public String toString();
    abstract public Piece clone();
    
    protected void addMove(Move m){
        this.list.add(m);
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkedList<Move> getList() {
        return list;
    }

    public void setList(LinkedList<Move> list) {
        this.list = list;
    }

    public LocationIn2DArray getLocation() {
        return location;
    }

    public void setLocation(LocationIn2DArray location) {
        this.location = location;
    }
}


