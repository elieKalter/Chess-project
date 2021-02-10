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
public class Pawn extends Piece{
    boolean wjmtft; //wjmtft = was just moved the first time

    public Pawn() {
        super();
        wjmtft = false;
    }
    
    public Pawn(Colors color) {
        super(color, 1);
        wjmtft = false;
    }
    
    public Pawn(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location){
        this(color, moved, list, location, false);
    }
    
    public Pawn(Colors color, boolean moved, LinkedList<Move> list, LocationIn2DArray location, boolean wjmtft){
        super(color, moved, 1, list, location);
        this.wjmtft = wjmtft;
    }
    
    public Pawn(Pawn p) {
        this.color = p.color;
        this.list = p.list;
        this.location = p.location;
        this.moved = p.moved;
        this.value = p.value;
        this.wjmtft = p.wjmtft;
    }
    
    @Override
    public void makeList(Tile[][] b) {
        this.list.clear();
        LocationIn2DArray tmp; // well hold places to check if possible to move to
        LocationIn2DArray tmp2; // well hold places to check some things about when checking if to move to a place held in tmp
        int tb = (this.color == Colors.White)? 1 : -1; //this var well determine if forward is towords the top or bottom
        
        //1 and 2 forward
        tmp = this.location.add(tb, 0); // 1 forward
        if (f.validLocation(tmp) && !b[tmp.getRow()][tmp.getColumn()].isOccupied()) {
            SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
            Move m = new Move(sourceDes, calcCosequences(b, sourceDes, tb, "one forward"));
            addMove(m);
            
            tmp.add(tb, 0); // 2 forward
            if (f.validLocation(tmp) && !this.moved && 
                    !b[tmp.getRow()][tmp.getColumn()].isOccupied()) {
                sourceDes.setDestination(tmp);
                m.setSourceAndDestination(sourceDes);
                m.setConsequences(calcCosequences(b, sourceDes, tb, "nothing"));
                addMove(m);
            }
        }
        
        //diagonal 1
        int direction = 1;
        addDiagonal(b, direction, tb);
        
        //diagonal 2
        direction = -1;
        addDiagonal(b, direction, tb);
    }    
    
    private void addDiagonal(Tile[][] b, int direction, int tb){
        LocationIn2DArray tmp = this.location.add(tb, direction);
        LocationIn2DArray tmp2;
        if (f.validLocation(tmp)) {
            if (b[tmp.getRow()][tmp.getColumn()].isOccupied()
                    && b[tmp.getRow()][tmp.getColumn()].getPiece().color != this.color) {
                SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                Move m = new Move(sourceDes, calcCosequences(b, sourceDes, tb, "takes"));
                addMove(m);
            }
            //en passant
            tmp2 = location.add(0, direction);
            Piece piece = b[tmp2.getRow()][tmp2.getColumn()].getPiece();
            if (piece.getClass() == this.getClass()) {
                Pawn p = (Pawn) piece;
                if(!b[tmp.getRow()][tmp.getColumn()].isOccupied()
                        && p.color != this.color
                        && p.wjmtft){
                    SourceAndDestinationLocations sourceDes = new SourceAndDestinationLocations(location, tmp);
                    Move m = new Move(sourceDes, calcCosequences(b, sourceDes, tb, "en passant"));
                    addMove(m);
                }
            }
        }
    }

    @Override
    public Pawn clone() {
        return new Pawn(this.color, this.moved, this.list, this.location);
    }
    
    private boolean onLastRow(SourceAndDestinationLocations sourceDes, int tb){
        return (sourceDes.getDestination().getRow() == 7 && tb == 1) || (sourceDes.getDestination().getRow() == 0 && tb == -1);
    }

    @Override
    public String toString() {
        if (this.color == Colors.White) {
            return "P";
        } else {
            return "p";
        }
    }

    @Override
    protected TypeOfConsequences calcCosequences(Tile[][] b, SourceAndDestinationLocations sourceDes, int tb, String info) {
        switch(info){
            case "one forward":{
                if (onLastRow(sourceDes, tb)) {
                    return TypeOfConsequences.promotion;
                } else {
                    return TypeOfConsequences.nothing;
                }
            }
            case "nothing":{
                return TypeOfConsequences.nothing;
            }
            case "takes":{
                if (onLastRow(sourceDes, tb)) {
                    return TypeOfConsequences.take_promotion;
                } else {
                    return TypeOfConsequences.take;
                }
            }
            case "en passant":{
                return TypeOfConsequences.en_passant;
            }
            default: return TypeOfConsequences.nothing;
        }
    }
}
