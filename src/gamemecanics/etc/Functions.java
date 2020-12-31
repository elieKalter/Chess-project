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
public class Functions {
    public boolean validLocation(LocationIn2DArray l){
        return l.getRow() >= 0 && l.getRow() < 8 && l.getColumn() >= 0 && l.getColumn() < 8;
    }
}
