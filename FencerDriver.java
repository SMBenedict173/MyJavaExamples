/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab3.lab3;

/**
 *
 * @author Max Benedict
 * @version 2/18/2020
 */
public class FencerDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instantiate a fencer named Jill on the Sharks team
        Fencer jill = new Fencer("Jill", "Sharks");
        //Instantiate a fencer named Stacy on the Dolphins team
        Fencer stacy = new Fencer ("Stacy", "Dolphins");
        System.out.println("Today's match will be between " + jill.toString() + 
                ", and " + stacy.toString() + ".");
        int jillVictories = 0;
        boolean round1 = jill.defeats(stacy);
        if (round1){
            jillVictories++;
            System.out.println("Jill has won the first round.");
        }
        else {
            System.out.println ("Stacy has won the first round.");
        }
        boolean round2 = stacy.defeats(jill);
        if (!round2){
            jillVictories++;
            System.out.println ("Jill has won the second round.");
            if (jillVictories == 2){
                System.out.println ("Jill has won the match!");
            }
        }
        else {
            System.out.println ("Stacy has won the second round.");
            if (jillVictories == 0){
                System.out.println ("Stacy has won the match!");
            }
        }
        if (jillVictories == 1){
            boolean round3 = jill.defeats(stacy);
            if (round3){
                System.out.println ("Jill has won the third round and the match!");
            }
            else {
                System.out.println ("Stacy has won the third round and the match");
            }
        }        
    }
    
}
