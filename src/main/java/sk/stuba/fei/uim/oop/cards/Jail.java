package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
public class Jail extends BlueCard {
    private final String name;
    private final String description;

    public Jail() {
        this.name = "Jail";
        this.description = "Puts a chosen player into jail.\nThere is a 25% chance he will break free and ignore this card.";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean playCard(Player[] players, Player currentPlayer) {
        Player toHit = choosePlayer(players, currentPlayer);
        for (var card : toHit.getCardsOnTable()){
            if (card instanceof Jail){
                System.out.println("Your target is already in jail!");
                return false;
            }
        }
        Jail jail = new Jail();
        toHit.placeCardOnTable(jail);
        System.out.println("You put " + toHit.getName() + " into jail!");
        return true;
    }

    public boolean checkEffect(int currentPlayerIndex, Player[] players, Card card) {
        Player inJail = players[currentPlayerIndex];
        if (!getChance(4)) {
            System.out.println(inJail.getName() + " couldn't escape from jail.\n" + inJail.getName() + " skips a turn");
            inJail.removeCard(card);
            return true;
        } else{
            System.out.println(inJail.getName() + " escaped from jail!");
            inJail.removeCard(card);
            return false;
        }
    }
}
