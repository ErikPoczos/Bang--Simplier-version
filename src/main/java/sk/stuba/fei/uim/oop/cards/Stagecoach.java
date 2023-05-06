package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Stagecoach extends BrownCard {
    private final String name;
    private final String description;

    public Stagecoach(){
        this.name = "Stagecoach";
        this.description = "Draw 2 cards.";
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
    public boolean playCard(Player[] players, Player currentPlayer){
        if (currentPlayer.getDeck().getTotalDrawableCards() + currentPlayer.getDeck().getDiscardedDeck().size() < 2){
            System.out.println("There arent enough cards! You cant play Stagecoach");
            return false;
        }
        System.out.println("You draw 2 cards.");
        currentPlayer.getDeck().drawCard(currentPlayer);
        currentPlayer.getDeck().drawCard(currentPlayer);
        return true;
    }
}
