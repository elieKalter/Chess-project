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
import static gamemecanics.pieces.Piece.f;

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

    public Rook(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location) {
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
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();
        
        addLocationsInDirection(b, -1, 0);
        addLocationsInDirection(b, 1, 0);
        addLocationsInDirection(b, 0, -1);
        addLocationsInDirection(b, 0, 1);
    }

    @Override
    public Rook clone() {
        return new Rook(this.color, this.moved, this.list, this.location);
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
