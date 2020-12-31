/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.etc;


import fbg.LocationIn2DArray;
/**
 *
 * @author elie
 */
public class MoveSourceDestination {
    private LocationIn2DArray source;
    private LocationIn2DArray destination;

    public MoveSourceDestination() {
    }

    public MoveSourceDestination(LocationIn2DArray source, LocationIn2DArray destination) {
        this.source = source;
        this.destination = destination;
    }

    public LocationIn2DArray getDestination() {
        return destination;
    }

    public void setDestination(LocationIn2DArray destination) {
        this.destination = destination;
    }

    public LocationIn2DArray getSource() {
        return source;
    }

    public void setSource(LocationIn2DArray source) {
        this.source = source;
    }
    
    
}
