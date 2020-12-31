/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.etc;

import java.util.LinkedList;

/**
 *
 * @author elie
 */
public class ListOfMoves {
    private LinkedList<MoveSourceDestination> listOfMoves;

    public ListOfMoves() {
        this.listOfMoves = new LinkedList<>();
    }

    public ListOfMoves(LinkedList<MoveSourceDestination> listOfLocations) {
        this.listOfMoves = listOfLocations;
    }

    public LinkedList<MoveSourceDestination> getListOfMoves() {
        return listOfMoves;
    }

    public void setListOfMoves(LinkedList<MoveSourceDestination> listOfMoves) {
        this.listOfMoves = listOfMoves;
    }
}
