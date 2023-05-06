package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Random;

public abstract class Card {
    private String name;
    private String description;
    private boolean isBlue;
    public Random random = new Random();

    public Card(){}
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBlue(){
        return isBlue;
    }
    public boolean playCard(Player[] players, Player currentPlayer) {
        return true;
    }

    public Player choosePlayer(Player[] players, Player currentPlayer){
        int index = ZKlavesnice.readInt("Choose a target to use the card by typing his number!");
        while (true){
            if ((index >= players.length || index < 0) || !players[index].isAlive()) {
                System.out.println("You chose an invalid player number!\n Try again!");
                index = ZKlavesnice.readInt("Choose a target to use the card by typing his number!");
            } else if (players[index] == currentPlayer) {
                System.out.println("You chose yourself!\n Try again!");
                index = ZKlavesnice.readInt("Choose a target to use the card by typing his number!");
            } else {
                break;
            }
        }
        return players[index];
    }
}
