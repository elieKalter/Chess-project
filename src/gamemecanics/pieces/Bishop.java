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
public class Bishop extends Piece {

    public Bishop() {
        super();
    }

    public Bishop(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location) {
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
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();
        
        addLocationsInDirection(b, -1, -1);
        addLocationsInDirection(b, -1, 1);
        addLocationsInDirection(b, 1, -1);
        addLocationsInDirection(b, 1, 1);
    }

    @Override
    public Bishop clone() {
        return new Bishop(this.color, this.moved, this.list, this.location);
    }
    
    private void addLocationsInDirection(Board b, int i, int j){
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        for (int k = 0; k < 8; k++) {
            tmp = this.location.add(i, j);
            if(!f.validLocation(tmp)){
                break;
            }
            if(!b.getBoard()[tmp.getRow()][tmp.getColumn()].isOccupied()){
                addMove(tmp);
            } else {
                if(b.getBoard()[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color){
                    addMove(tmp);
                }
                break;
            }
        }
    }
}
