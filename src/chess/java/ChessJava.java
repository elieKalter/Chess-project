/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.java;

import fbg.Colors;
import gamemecanics.board.Board;
import gamemecanics.etc.Move;
import gamemecanics.etc.SourceAndDestinationLocations;
import gamemecanics.pieces.Piece;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author elie
 */
public class ChessJava {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board(true);
        b.print();
        play(b);
    }
    
    public static void play(Board b){
        boolean turn = true;
        Colors c;
        
        while (turn) {
            SourceAndDestinationLocations sd = new SourceAndDestinationLocations();
            c = turn ? Colors.White : Colors.Black ;
            b.getMoves(c);
            do {                
                sd = getMove();
            } while (checkForMoveInList(b, sd, c));
            turn = !turn;
        }
    }
    
    public static SourceAndDestinationLocations getMove(){
        SourceAndDestinationLocations sd = new SourceAndDestinationLocations();
        Scanner s = new Scanner(System.in);
        
        System.out.println("enter row source");
        sd.getSource().setRow(s.nextInt());
        System.out.println("enter column source");
        sd.getSource().setColumn(s.nextInt());
        System.out.println("enter row destination");
        sd.getDestination().setRow(s.nextInt());
        System.out.println("enter column destination");
        sd.getDestination().setColumn(s.nextInt());
        
        return sd;
    }
    
    public static boolean checkForMoveInList(Board b, SourceAndDestinationLocations sd, Colors c){
        int wb = c == Colors.White ? 0 : 1 ;
        for (int i = 0; i < b.getPiecesOnBoard()[wb].size(); i++) {
            if(b.getPiecesOnBoard()[wb].get(i).getLocation() == sd.getSource()){
                LinkedList<Move> listOfMoves = b.getPiecesOnBoard()[wb].get(i).getList();
                for (int j = 0; j < listOfMoves.size(); j++) {
                    if (listOfMoves.get(i).getSourceAndDestination().equals(sd)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
