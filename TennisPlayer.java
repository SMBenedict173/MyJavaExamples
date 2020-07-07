/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab1.lab1;

/**
 *
 * @author Max Benedict
 * @version 1.0
 */
public class TennisPlayer implements Competable{
    private int strength;
    private String name;
    private String team;
    private int serve;
    
    /**
     * Default constructor
     * Creates a tennis player with no name or team and 50 strength and serve
     */
    public TennisPlayer(){
        name = "";
        strength = 50;
        team = "";
        serve = 50;
    }
    
    /**
     * Creates a tennis player with user chosen attributes
     * @param newName
     * @param newStrength, between 1 and 100
     * @param newTeam 
     * @param newServe between 1 and 100
     */
    public TennisPlayer(String newName, int newStrength, String newTeam, int   
            newServe){
        name = newName;
        strength = newStrength;
        team = newTeam;
        serve = newServe;
    }
     /**
     * Increase the strength of a competitor 
     *
     * @param  strength an int that represents a strength increment
     */
    @Override
    public void makeStronger(int strength){
        this.strength = this.strength + strength;
        if (this.strength > 100){
            this.strength = 100;
        }
    }
    
    /** 
     * Decrease the strength of a competitor
     * 
     * @param strength an int that represents a strength decrement
     */
    @Override
    public void makeWeaker(int strength){
        this.strength = this.strength - strength;
        if (this.strength < 1){
            this.strength = 1;
        }
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
     * If player has superior service skill, they will gain an advantage in strength
     * @return boolean returns true if object wins over opponent, false otherwise (tie goes to opponent)
     */
    @Override
    public boolean wins(Competable opponent){
        if (this.compareServe((TennisPlayer)opponent) == true){
            opponent.makeWeaker(5);
            this.makeStronger(5);
        }
        else {
            opponent.makeStronger(5);
            this.makeWeaker(5);
        }
        if (this.compareStrength((TennisPlayer)opponent) == true){
            return true;  
        }
        else {
            return false;
        }
        
    }
    
    /**
     * 
     * @return integer representing the player's service skill
     */
    public int getServe(){
        return this.serve;
    }
    
    /**
     * 
     * @param opponent
     * @return boolean on whether or not the player has the superior
     *  service skill to the opponent
     */
    private boolean compareServe(TennisPlayer opponent){
        if (this.getServe() > opponent.getServe()){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * 
     * @param opponent
     * @return boolean on whether or not the player has superior or 
     * equivalent strength to the opponent
     */
    private boolean compareStrength (TennisPlayer opponent){
        if (this.strength > opponent.strength){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Returns the Player's name and team in String form
     */
    @Override
    public String toString(){
        return this.getName() + ", playing for " + this.getTeam();
    }
    
    /**
     * @param other player
     * @return true if the players are the same person or exact duplicates
     */
    @Override
    public boolean equals(Object player){
        if (this.getName() != ((Competable)player).getName()){
            return false;
        }
        else if(this.getTeam() != ((Competable)player).getTeam()){
            return false;
            }
            else if (this.strength != ((TennisPlayer)player).strength){
                return false;
            }
            else if (this.serve != ((TennisPlayer)player).serve){
                return false;
            }
            else {
                return true;
            }
    }
    
    
}
