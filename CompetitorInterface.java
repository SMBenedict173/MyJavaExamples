
/**
 * An interface for standardizing classes of competitors, including athletes,
 * warriors, or any types of objects that might compete
 *
 * @author David Luginbuhl
 * @version 2.0
 */
package com.lab3.lab3;
        
public interface CompetitorInterface
{
    
    /**
     * Get the name of a competitor
     * 
     * @return String that represents the competitor's name
     */
    public String getName();
    
    /**
     * Get the name of the competitor's team
     * 
     * @return String that represent's the competitor's team name
     */
    public String getTeam();
    
    /** 
     * Fights an opponent
     * 
     * @param opponent a Competable object that represents the opponent
     * @return boolean returns true if object wins over opponent, false otherwise (tie goes to opponent)
     */
    public boolean defeats(CompetitorInterface opponent);
}
