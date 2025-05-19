
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.turnbasedstack;

/**
 *
 * @author Student's Account
 */
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;

public class TurnBasedStack {

    public static void main(String[] args) {

        Stack<Integer> lastHP = new Stack<>();

        int stunCooldown = 0;

        Scanner scan = new Scanner(System.in);

        Random random = new Random();

        int playerHP = 1000;
        int botHP = 500;
        int playerMaxDmg = 10;
        int playerMinDmg = 5;
        int botMaxDmg = 10;
        int botMinDmg = 5;

        while (true) {

            if (botHP <= 0) {
                System.out.println("The Enemy has died");
                break;
            }
            if (playerHP <= 0) {
                System.out.println("Player has died!");
                break;
            }
            // player's turn
            System.out.print("Player HP: " + playerHP);
            System.out.println("      Monster HP: " + botHP);
            System.out.println(" ");
            System.out.println("What would you like to do?");
            System.out.println("1 Attack");
            System.out.println("2 Stun");
            System.out.println("3 Skip");

            System.out.println(" ");
            System.out.print("Pick a choice: ");

            String in = scan.nextLine();

            if (in.equalsIgnoreCase("Exit")) {
                System.out.println("You exit the game");
                break;
            }
            if (in.equalsIgnoreCase("1")) {
                int playerDmg = playerMaxDmg - random.nextInt(playerMinDmg);
                System.out.println(" ");
                System.out.println("You Dealt " + playerDmg + " damage to the Enemy");
                lastHP.push(botHP);
                botHP = botHP - playerDmg;
                if (botHP <= 0) {
                    botHP = 0;
                }
                System.out.println("The enemy has " + botHP + " HP reamaining.");
                System.out.println(" ");
            } else if (in.equalsIgnoreCase("2")) {
                if (stunCooldown == 0) {
                    System.out.println(" ");
                    System.out.println("The enemy has been stunned!");
                    System.out.println("Your turn to attack");
                    System.out.println(" ");
                    stunCooldown = 4; // Set cooldown
                    continue;
                } else {
                    System.out.println(" ");
                    System.out.println("Stun is on cooldown for " + stunCooldown + " more turns.");
                    System.out.println(" ");
                    continue;
                }
            } else if (in.equalsIgnoreCase("3")) {
                System.out.println(" ");
                System.out.println("You skip your turn");
                System.out.println(" ");
                System.out.println("Enemies turn to attack");
                System.out.println(" ");
            }

            int passiveChance = random.nextInt(4);
            if (!lastHP.isEmpty() && passiveChance == 0) {
                int Return = lastHP.peek();
                System.out.println("Enemies passive has been activitade");
                System.out.println("Enemies health return to " + Return + " HP");
                botHP = Return;
                System.out.println();
            }

            //Enemy turn
            int botDmg = botMaxDmg - random.nextInt(botMinDmg);
            System.out.println("It is the monster's turn now");
            System.out.println(" ");
            System.out.println("The Monster Dealt " + botDmg + " damage to the Player");
            playerHP = playerHP - botDmg;
            if (playerHP <= 0) {
                playerHP = 0;
            }
            System.out.println("The Player has " + playerHP + " HP remaing");
            System.out.println(" ");

            if (stunCooldown > 0) {
                stunCooldown--;

            }
        }
    }
}
