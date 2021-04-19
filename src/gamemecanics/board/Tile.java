/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.board;

import gamemecanics.pieces.Piece;
/**
 *
 * @author elie
 */
public class Tile {
    private boolean occupied;
    private Color color;
    private Piece piece;

    public Tile(Color c) {
        this.color = c;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.occupied = piece != null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    enum Color {
        white,
        black
    }
}
