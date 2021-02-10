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
import gamemecanics.etc.Move.TypeOfConsequences;
import gamemecanics.etc.SourceAndDestinationLocations;
import java.util.LinkedList;

/**
 *
 * @author elie
 */
public class Bishop extends Piece {

    public Bishop() {
        super();
    }
    
    public Bishop(Colors color) {
        super(color, 3);
    }

    public Bishop(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location) {
        super(color, moved, 3, list, location);
    }
    
    public Bishop(Bishop b) {
        this.color = b.color;
        this.list = b.list;
        this.location = b.location;
        this.moved = b.moved;
        this.value = b.value;
    }

    @Override
    public void makeList(Tile[][] b) {
        this.list.clear();
        
        addLocationsInDirection(b, -1, -1);
        addLocationsInDirection(b, -1, 1);
        addLocationsInDirection(b, 1, -1);
        addLocationsInDirection(b, 1, 1);
    }

    @Override
    public Bishop clone() {
        return new Bishop(this.color, this.moved, this.list, this.location);
    }
    
    private void addLocationsInDirection(Tile[][] b, int i, int j){
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        for (int k = 0; k < 8; k++) {
            tmp = this.location.add(i, j);
            if(!f.validLocation(tmp)){
                break;
            }
            if(!b[tmp.getRow()][tmp.getColumn()].isOccupied()){
                SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                    Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
                    addMove(m);
            } else {
                if(b[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color){
                    SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                    Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
                    addMove(m);
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        if (this.color == Colors.White) {
            return "B";
        } else {
            return "b";
        }
    }

    @Override
    protected TypeOfConsequences calcCosequences(Tile[][] b, SourceAndDestinationLocations sourceDes, int tb, String info) {
        Tile desTile = b[sourceDes.getDestination().getRow()][sourceDes.getDestination().getColumn()];
        if (desTile.isOccupied()) {
            return Move.TypeOfConsequences.take;
        } else {
            return Move.TypeOfConsequences.nothing;
        }
    }
}
