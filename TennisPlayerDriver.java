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
public class TennisPlayerDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a player named Bill with decent strenght and service skill
        TennisPlayer bill = new TennisPlayer ("Bill", 67, "Providence Peacocks", 54);
        // Create a player named Steve with low strenght but excellent service skill
        TennisPlayer steve = new TennisPlayer ("Steve", 45, "New York Nancies", 89);
        // Announce the match
        System.out.println("Today's match will be between " + bill.toString() + 
                ", and " + steve.toString() + ".");
        //Announce the winner
        if (bill.wins(steve) == true){
            System.out.println ("Bill is victorious!");
        }
        else {
            System.out.println("Steve is victorious");
        }
        //Make sure bill is himself
        System.out.println("Is Bill still Bill?");
        System.out.println(bill.equals(bill));
    }
    
}
