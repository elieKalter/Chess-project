/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.pieces;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.board.Tile;
import gamemecanics.etc.Move;
import gamemecanics.etc.SourceAndDestinationLocations;
import java.util.LinkedList;

/**
 *
 * @author elie
 */
public class Knight extends Piece{

    public Knight() {
        super();
    }
    
    public Knight(Colors color){
        super(color, 3);
    }
    
    public Knight(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location){
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
    public void makeList(Tile[][] b) {
        this.list.clear();
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        
        //i = -2;
        //j = -1;
        tmp = this.location.add(-2, -1);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //j = 1;
        tmp = this.location.add(-2, 1);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m); 
        }
        
        //i = -1;
        //j = -2;
        tmp = this.location.add(-1, -2);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //j = 2;
        tmp = this.location.add(-1, 2);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //i = 1;
        //j = -2;
        tmp = this.location.add(1, -2);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //j = 2;
        tmp = this.location.add(1, 2);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //i = 2;
        //j = -1;
        tmp = this.location.add(2, -1);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
        
        //j = 1;
        tmp = this.location.add(2, 1);
        if(addLocation(b, tmp)){
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
            addMove(m);
        }
    }

    @Override
    public Knight clone() {
        return new Knight(this.color, this.moved, this.list, this.location);
    }
    
    private boolean addLocation(Tile[][] b, LocationIn2DArray l){
        return f.validLocation(l)
                && (!b[l.getRow()][l.getColumn()].isOccupied()
                || b[l.getRow()][l.getColumn()].getPiece().color != this.color);
    }

    @Override
    public String toString() {
        if (this.color == Colors.White) {
            return "N";
        } else {
            return "n";
        }
    }

    @Override
    protected Move.TypeOfConsequences calcCosequences(Tile[][] b, SourceAndDestinationLocations sourceDes, int tb, String info) {
        Tile desTile = b[sourceDes.getDestination().getRow()][sourceDes.getDestination().getColumn()];
        if (desTile.isOccupied()) {
            return Move.TypeOfConsequences.take;
        } else {
            return Move.TypeOfConsequences.nothing;
        }
    }
}
