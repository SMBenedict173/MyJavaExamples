/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.queue.lab5;
import java.util.Scanner;

/**
 *
 * @author Stephen Benedict, April 4, 2020
 */

        
public class FibonacciSequence {
   /**
    * @param n
    * @return Fibonacci number in sequence position n
    */
    public static int computeFibonacci(int n) {
        int fibNum = 0;
        if (n == 0){
            //nothing needs to be done in this case as the value of fibNum is
            //already correct for this base case at sequence position 0
        }
        else if (n == 1){
            //this is the second base case at sequence position 1
            fibNum = 1;
        }
        else {
            // this will handle all input for sequence positions  2 and greater
            // takes the sum of the previous two values in the sequence via recursion
            fibNum = computeFibonacci(n-2) + computeFibonacci(n-1); 
        }
        return fibNum;
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      //User Prompt
      System.out.println("This program will provide the Fibonacci number at the position"
              + "in the sequence provided by the user");
      System.out.println("Please enter only integers greater than or equal to 0");
      //User input
      int inputNumber = scnr.nextInt();
      if (inputNumber < 0){
            // Handles negative integer input.
            System.out.println ("What are you doing? Please only enter either 0 or "
                    + "a positive integer.");
            System.out.println("Please run this program again with appropriate input.");
            }
      else{
        //Result
         System.out.println("F_" + inputNumber + " is " + computeFibonacci(inputNumber));
      }   
   }
}
