package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Miss extends BrownCard {
    private final String name;
    private final String description;

    public Miss(){
        this.name = "Miss";
        this.description =  "Enables you to dodge a bang card. You can't play this card. " +
                            "It will be automatically used when someone plays the Bang card on you";
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
        System.out.println("You cant play a miss card! It will be automatically played when someone uses a bang card on you!");
        return false;
    }
}
