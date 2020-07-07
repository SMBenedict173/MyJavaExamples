package com.lab1.lab1;
/**
 * An interface for standardizing classes of competitors, including athletes,
 * warriors, or any types of objects that might compete
 *
 * @author David Luginbuhl
 * @version 1.0
 */
public interface Competable
{
    /**
     * Increase the strength of a competitor 
     *
     * @param  strength an int that represents a strength increment
     */
    void makeStronger(int strength);
    
    /** 
     * Decrease the strength of a competitor
     * 
     * @param strength an int that represents a strength decrement
     */
    void makeWeaker(int strength);
    
    /**
     * Get the name of a competitor
     * 
     * @return String that represents the competitor's name
     */
    String getName();
    
    /**
     * Get the name of the competitor's team
     * 
     * @return String that represent's the competitor's team name
     */
    String getTeam();
    
    /** 
     * Fights an opponent
     * 
     * @param opponent a Competable object that represents the opponent
     * @return boolean returns true if object wins over opponent, false otherwise (tie goes to opponent)
     */
    boolean wins(Competable opponent);
}
