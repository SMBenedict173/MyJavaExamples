/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab3.lab3;
import java.util.Random;

/**
 *
 * @author jbene
 */
public class Fencer implements CompetitorInterface {
    private String name;
    private String team;
    private LinkedBag <String> moves = new LinkedBag();
    
    /** Default Constructor
     * Creates a Fencer with no name, no team, and the three moves available to
     * all fencers (Lunge, Swipe, and Parry)
     */
    public Fencer(){
        this.name = null;
        this.team = null;
        this.moves.add("Lunge");
        this.moves.add ("Swipe");
        this.moves.add ("Parry");
    }
    
    /** Customizable Constructor
     * @param customName for the fencer
     * @param customTeam for the fencer's team
     * Creates a Fencer with the specified name and team and the three moves available 
     * to all fencers (Lunge, Swipe, and Parry)
     */
    public Fencer (String customName, String customTeam){
        this.name = customName;
        this.team = customTeam;
        this.moves.add("Lunge");
        this.moves.add ("Swipe");
        this.moves.add ("Parry");
    }
    
    /**
     * Get the name of a competitor
     * 
     * @return String that represents the competitor's name
     */
   @Override
    public String getName(){
        return this.name;
    }
    
    /**
     * Get the name of the competitor's team
     * 
     * @return String that represents the competitor's team name
     */
    @Override
    public String getTeam(){
        return this.team;
    }
    
    /** 
     * Fights an opponent
     * 
     * @param opponent a Competable object that represents the opponent
     * @return boolean returns true if object wins over opponent, false otherwise 
     * (tie goes to opponent) Lunge beats swipe, parry beats lunge,
     * and swipe beats parry
     */
    @Override
    public boolean defeats (CompetitorInterface opponent){
        // Create an array of the fencer's moves
        Object[] fencerMoves = this.moves.toArray();
        // Create an array of the opponent's moves
        @SuppressWarnings("unchecked")
        Object[] opponentMoves = ((Fencer) opponent).moves.toArray();
        //unchecked cast
        // Instantiate a random number generator
        Random randomizer = new Random();
        //Randomly chooses the fencer's move
        String fencerAttack = (String)fencerMoves[randomizer.nextInt(2)];
        //Randomly chooses the opponent's move
        String opponentAttack = (String)opponentMoves[randomizer.nextInt(2)];
        // Of the nine possible pairings, only three result in victory (see above)
        boolean result = false;
        if ((fencerAttack.equals("Lunge")) && (opponentAttack.equals("Swipe"))){
            result = true;
        }
        else if ((fencerAttack.equals("Parry")) && (opponentAttack.equals("Lunge"))){
            result = true;
        }
        else if ((fencerAttack.equals("Swipe")) && (opponentAttack.equals("Parry"))){
            result = true;
        }
        return result;
    }
    
    /**
     * @return String representation of a fencer
     * 
     */
    @Override
    public String toString(){
        return this.getName() + ", on the " + this.getTeam() + " team";
    }
    
    /**
     * @return boolean on whether or not this fencer and another fencer are the same
     */
    @Override
    public boolean equals(Object other){
        Fencer otherFencer = (Fencer) other;
        boolean result = false;
        if ((this.getName().equals(otherFencer.getName())) && (this.getTeam().equals(
            otherFencer.getTeam()))){
            result = true;
        }
        return result;
    }
}
