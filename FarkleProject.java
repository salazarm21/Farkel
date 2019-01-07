package farkleproject;

import java.util.Scanner;

/**
 *This project is a game of farkle. The purpose of the game is too gain the desired amount of points before your opponent by rolling dice. 
 * @author Marina Salazar CSCI 180 Project 2
 *
 */
public class FarkleProject {

    public static String playerOne;
    public static String playerTwo;
    public static String currentPlayer;
    public static int score1;
    public static int score2;
    public static int currentScore;
    public static String playerInput;
    public static int die1;
    public static int die2;
    public static int die3;
    public static int die4;
    public static int pointsEarned;
    public static int dieLeft = 4;
    public static String allRolled;
    public static int pointsNeeded;
    public static int diceUsed;
    public static int banked1;
    public static int banked2;


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Let's Play Farkle!" + '\n'); // Greeting to frakle Game
        Scanner input = new Scanner(System.in);
        System.out.print("Enter player 1 name: "); //Asks player to input first person's name
        playerOne = input.nextLine();
        System.out.print("Enter player 2 name: "); //Asks player to input second person's name
        playerTwo = input.nextLine();
        System.out.print("How many points do you need to win? "); //Asks player to input how many points they will play too
        pointsNeeded = input.nextInt();
        System.out.println("Game will play until a player scores " + pointsNeeded + " points" + '\n'); //Tells user how many points game will play too
        String determinePlayerOne = determinePlayerOne();
        turn();
    }

    //Determines which player will go first in the game
    public static String determinePlayerOne() {
        //Sets player one 
        if (die1 > die2) {
            System.out.println(playerOne + " rolled a " + die1);
            System.out.println(playerTwo + " rolled a " + die2);
            System.out.println(playerOne + " is Player One!");
            currentPlayer = playerOne;
            banked1 = score1;
            return playerOne;
            
        } else if (die1<die2)
        {
            //sets player two
            System.out.println(playerOne + " rolled a " + die1);
            System.out.println(playerTwo + " rolled a " + die2);
            System.out.println(playerTwo + " is Player One!" + '\n');
            currentPlayer = playerTwo;
            banked1 = score1;            
            return playerTwo;
        } else
        { 
            //rerolls if the two dies are equal
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
            return determinePlayerOne();
        }
    }

//Plays the frakle game
//Gives the current player instructions on what to input    
    private static void turn() {
        Scanner input = new Scanner(System.in);
        //Runs the game for player one
        if (currentPlayer.equals(playerOne)) {
            //pointsEarned = 0;
            System.out.println("Total Points " + playerOne + ": " + score1 + "  " + playerTwo + ": " + score2);
            System.out.println(currentPlayer + "'s turn. Banked " + banked1 + " points. Die Left: " + dieLeft);
            System.out.println(currentPlayer + ", Enter a 1 to roll or a 2 to bank points and stop: ");
            playerInput = input.nextLine();
            if (null == playerInput) {
                System.out.println("That is not a valid input. Try again" + '\n');
                turn();
            } else { 
                switch (playerInput) {
                    //runs if the player one inputs 2 to quit turn
                    case "2":
                        score1 = score1 + banked1;
                        banked1 = 0;
                        //checkDiceCount();
                        switchCurrentPlayer();
                        
                        //diceUsed = 4;
                        break;
                    //runs if player one inputs 1 to roll and continue turn
                    case "1":
                        checkDiceCount();
                        diceRolls();
                        dieLefttoRoll();
                        banked1 += pointsEarned;
                                                
                        break;
                    default:
                        System.out.println("That is not a valid input. Try again");
                        turn();
                        break;
                }
                checkWinner();
            }

            
        } //runs the game for player two
        else if (currentPlayer.equals(playerTwo)) {
            //pointsEarned = 0;
            System.out.println("Total Points " + playerOne + ": " + score1 + "  " + playerTwo + ": " + score2);
            System.out.println(currentPlayer + "'s turn. Banked " + banked2 + " points. Die Left: " + dieLeft);
            System.out.println(currentPlayer + ", Enter a 1 to roll or a 2 to bank points and stop: ");
            playerInput = input.nextLine();
            if (null == playerInput) {
                System.out.println("That is not a valid input. Try again" + '\n');
                turn();
            } else {
                switch (playerInput) {
                   //runs if the player two inputs 2 to quit turn
                    case "2":
                        score2 = score2 + banked2;
                        banked2 = 0;
                        switchCurrentPlayer();

                        break;
                   //runs if the player two inputs 2 to quit turn
                    case "1":
                        checkDiceCount();
                        diceRolls();
                        dieLefttoRoll();
                        banked2 += pointsEarned;
                     
                        break;
                    default:
                        System.out.println("That is not a valid input. Try again");
                        turn();
                        break;
                }
            }
            checkWinner();

        }
    }
//Switches the players and points

    private static void switchCurrentPlayer() {
        if (currentPlayer.equals(playerOne)) {
            dieLeft = 4;
            currentPlayer = playerTwo;
            
            pointsEarned = 0;
        } else {
            dieLeft = 4;
            currentPlayer = playerOne;
            
            pointsEarned = 0;
        }
    }
    //Determines what die were rolled and how many points are accquired 

    private static void diceRolls() {

        if ((die1 != die2) && (die2 !=  die3) && (die1 != die3) && (die3 != die4) && (die4 != die2) && (die4 != die1)) {
            pointsEarned = 3000;
            diceUsed = 0;
        } else if ((die1 == 6 && die4 == 6) || (die1 == 6 && die2 == 6) || (die2 == 6 && die3 == 6) || (die1 == 6 && die3 == 6) || (die3 == 6 && die4 == 6) || (die4 == 6 && die2 == 6)) {
            pointsEarned = 600;
            diceUsed = 2;
        } else if ((die1 == 5 && die2 == 5) || (die2 == 5 && die3 == 5) || (die1 == 5 && die3 == 5) || (die3 == 5 && die4 == 5) || (die4 == 5 && die2 == 5) || (die4 == 5 && die1 == 5)) {
            pointsEarned = 500;
            diceUsed = 2;
        } else if ((die1 == 4 && die2 == 4) || (die2 == 4 && die3 == 4) || (die1 == 4 && die3 == 4) || (die3 == 4 && die4 == 4) || (die4 == 4 && die2 == 4) || (die4 == 4 && die1 == 4)) {
            pointsEarned = 400;
            diceUsed = 2;
        } else if ((die1 == 3 && die2 == 3) || (die2 == 3 && die3 == 3) || (die1 == 3 && die3 == 3) || (die3 == 3 && die4 == 3) || (die4 == 3 && die2 == 3) || (die4 == 3 && die1 == 3)) {
            pointsEarned = 300;
            diceUsed = 2;
        } else if ((die1 == 2 && die2 == 2) || (die2 == 2 && die3 == 2) || (die1 == 2 && die3 == 2) || (die3 == 2 && die4 == 2) || (die4 == 2 && die2 == 2) || (die4 == 2 && die1 == 2)) {
            pointsEarned = 200;
            diceUsed = 2;
        } else if ((die1 == 1 && die2 == 1) || (die2 == 1 && die3 == 1) || (die1 == 1 && die3 == 1) || (die3 == 1 && die4 == 1) || (die4 == 1 && die2 == 1) || (die4 == 1 && die1 == 1)) {
            pointsEarned = 1000;
            diceUsed = 2;
        } else if (die1 == 5 || die2 == 5 || die3 == 5 || die4 == 5) {
            pointsEarned = 50;
             if(diceUsed == 2){
                    dieLeft = 1;
                }
                else{
                diceUsed = 2;
                }
            //diceUsed = dieLeft;
        } else if (die1 == 1 || die2 == 1 || die3 == 1 || die4 == 1) {
            pointsEarned = 100;
                if(diceUsed == 2){
                    diceUsed = 1;
                }
                else{
                diceUsed = 2;
                }
            //diceUsed = dieLeft ;
        } else {
            // tells the player they farkled
            if(currentPlayer.equals(playerOne)){
            pointsEarned = 0;
            dieLeft = 0;
            System.out.println("Farkle!");
            switchCurrentPlayer();
        }
            else if(currentPlayer.equals(playerTwo)){
            pointsEarned = 0;
            dieLeft = 0;
            System.out.println("Farkle!");
            switchCurrentPlayer();
        }
        }
    }

    //checks how many die were used
    private static void checkDiceCount() {
        switch (diceUsed) {
            case 0:
                
                die1 = (int) (Math.random() * 6) + 1;
                die2 = (int) (Math.random() * 6) + 1;
                die3 = (int) (Math.random() * 6) + 1;
                die4 = (int) (Math.random() * 6) + 1;
                System.out.println(currentPlayer + " rolled  " + die1 + " " + die2 + " " + die3 + " " + die4);
                break;
            case 1:
                
                die1 = (int) (Math.random() * 6) + 1;
                die2 = (int) (Math.random() * 6) + 1;
                die3 = (int) (Math.random() * 6) + 1;
                die4 = 0;
                System.out.println(currentPlayer + " rolled  " + die1 + " " + die2 + " " + die3);
                break;
            case 2:
                
                die1 = (int) (Math.random() * 6) + 1;
                die2 = (int) (Math.random() * 6) + 1;
                die3 = 0;
                die4 = 0;
                System.out.println(currentPlayer + " rolled  " + die1 + " " + die2);
                break;
            case 3:
                
                die1 = (int) (Math.random() * 6) + 1;
                die2 = 0;
                die3 = 0;
                die4 = 0;
                System.out.println(currentPlayer + " rolled  " + die1);
                break;
            case 4:
                
                die1 = 0;
                die2 = 0;
                die3 = 0;
                die4 = 0;
                //System.out.println(currentPlayer + " rolled  " + die1 + " " + die2);
                System.out.println("Turn is up. No more Die");
                switchCurrentPlayer();
                break;
            default:
                break;
        }
    }

//Checks if someone won farkle
    public static void checkWinner() {
        if (score1 >= pointsNeeded) {
            System.out.println(currentPlayer + " won with: " + score1 + " points!");

        } else if (score2 >= pointsNeeded) {
            System.out.println(currentPlayer + " won with: " + score2 + " points!");
        } else {
            turn();
        }
    }

    //determines how many die are left
    public static void dieLefttoRoll() {
        switch (diceUsed) {
            case 4:
                dieLeft = 0;
                break;
            case 3:
                dieLeft = 1;
                break;
            case 2:
                dieLeft = 2;
                break;
            case 1:
                dieLeft = 3;
                break;
            case 0:
                dieLeft= 4;
                break;
            default:
                break;
        }
        
    }
    
}
