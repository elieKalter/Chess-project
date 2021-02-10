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
public class King extends Piece{

    public King() {
        super();
    }
    
    public King(Colors color) {
        super(color, 10);
    }
    
    public King(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location){
        super(color, moved, 10, list, location);
    }
    
    public King(King k) {
        this.color = k.color;
        this.list = k.list;
        this.location = k.location;
        this.moved = k.moved;
        this.value = k.value;
    }

    @Override
    public void makeList(Tile[][] b) {
        this.list.clear();
        LocationIn2DArray tmp;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(i == 0 && j == 0){
                    continue;
                }
                tmp = this.location.add(i, j);
                if(f.validLocation(tmp) 
                        && (!b[tmp.getRow()][tmp.getColumn()].isOccupied()
                        || b[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color)){
                    SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                    Move m = new Move(sourceDes, calcCosequences(b, sourceDes, 0, ""));
                    addMove(m);
                }
            }
        }
    }

    @Override
    public Piece clone() {
        return new King(this.color, this.moved, this.list, this.location);
    }

    @Override
    public String toString() {
        if (this.color == Colors.White) {
            return "K";
        } else {
            return "k";
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
