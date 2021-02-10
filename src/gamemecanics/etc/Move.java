/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemecanics.etc;

/**
 *
 * @author elie
 */
public class Move {
    private SourceAndDestinationLocations  sourceAndDestination;
    private TypeOfConsequences consequences;

    public Move() {
    }

    public Move(SourceAndDestinationLocations sourceAndDestination, TypeOfConsequences consequences) {
        this.sourceAndDestination = sourceAndDestination;
        this.consequences = consequences;
    }

    public SourceAndDestinationLocations getSourceAndDestination() {
        return sourceAndDestination;
    }

    public void setSourceAndDestination(SourceAndDestinationLocations sourceAndDestination) {
        this.sourceAndDestination = sourceAndDestination;
    }

    public TypeOfConsequences getConsequences() {
        return consequences;
    }

    public void setConsequences(TypeOfConsequences consequences) {
        this.consequences = consequences;
    }
    
    
    public enum TypeOfConsequences {
        nothing,
        take,
        castle,
        en_passant,
        promotion,
        take_promotion
    }
}