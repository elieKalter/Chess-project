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
public class Queen extends Piece{

    public Queen() {
        super();
    }
    
    public Queen(Colors color){
        super(color, 9);
    }

    public Queen(Colors color, boolean moved, ListOfMoves list, LocationIn2DArray location) {
        super(color, moved, 9, list, location);
    }
    
    public Queen(Queen q) {
        this.color = q.color;
        this.list = q.list;
        this.location = q.location;
        this.moved = q.moved;
        this.value = q.value;
    }

    @Override
    public void makeList(Board b) {
        this.list.getListOfMoves().clear();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(i == 0 && j == 0){
                    continue;
                }
                addLocationsInDirection(b, i, j);
            }
        }
    }

    @Override
    public Piece clone() {
        return new Queen(this.color, this.moved, this.list, this.location);
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
