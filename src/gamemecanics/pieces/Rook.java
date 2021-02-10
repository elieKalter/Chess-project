/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.pieces;

import fbg.Colors;
import fbg.LocationIn2DArray;
import gamemecanics.board.Tile;
import gamemecanics.etc.SourceAndDestinationLocations;
import gamemecanics.etc.Move;
import static gamemecanics.pieces.Piece.f;
import java.util.LinkedList;
import gamemecanics.etc.Move.TypeOfConsequences;

/**
 *
 * @author elie
 */
public class Rook extends Piece{

    public Rook() {
        super();
    }
    
    public Rook(Colors color){
        super(color, 5);
    }

    public Rook(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location) {
        super(color, moved, 5, list, location);
    }
    
    public Rook(Rook r) {
        this.color = r.color;
        this.list = r.list;
        this.location = r.location;
        this.moved = r.moved;
        this.value = r.value;
    }

    @Override
    public void makeList(Tile[][] b) {
        this.list.clear();
        
        addLocationsInDirection(b, -1, 0);
        addLocationsInDirection(b, 1, 0);
        addLocationsInDirection(b, 0, -1);
        addLocationsInDirection(b, 0, 1);
    }

    @Override
    public Rook clone() {
        return new Rook(this.color, this.moved, this.list, this.location);
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
                SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
                addMove(m);
                break;
            }
        }
    }

    @Override
    public String toString() {
        if(this.color == Colors.White){
            return "R";
        }else{
            return "r";
        }
    }

    @Override
    protected TypeOfConsequences calcCosequences(Tile[][] b, SourceAndDestinationLocations sourceDes, int tb, String info) {
        Tile desTile = b[sourceDes.getDestination().getRow()][sourceDes.getDestination().getColumn()];
        if (desTile.isOccupied()) {
            return TypeOfConsequences.take;
        } else {
            return TypeOfConsequences.nothing;
        }
    }
}
