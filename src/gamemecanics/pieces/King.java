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
public class King extends Piece{

    public King() {
        super();
    }
    
    public King(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location){
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
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();
        LocationIn2DArray tmp;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(i == 0 && j == 0){
                    continue;
                }
                tmp = this.location.add(i, j);
                if(f.validLocation(tmp) 
                        && (!b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()
                        || b.getBoard()[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color)){
                    addMove(tmp);
                }
            }
        }
    }

    @Override
    public Piece clone() {
        return new King(this.color, this.moved, this.list, this.location);
    }
    
}
