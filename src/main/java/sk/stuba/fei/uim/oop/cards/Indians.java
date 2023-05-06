package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends BrownCard {
    private final String name;
    private final String description;

    public Indians() {
        this.name = "Indians";
        this.description = "Every player besides you will get hit by indians.\nIf they don't have a Bang card, they lose a life.";
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
        System.out.println("Lookout! Indians are attacking!");
        boolean usedBang;
        for (var toHit : players) {
            usedBang = false;
            if (currentPlayer != toHit) {
                for (var card : toHit.getCardsInHand()){
                    if (card instanceof Bang){
                        System.out.println(toHit.getName() + " used a Bang! card on the indians!");
                        toHit.removeCard(card);
                        usedBang = true;
                        break;
                    }
                }
                if (!usedBang && toHit.isAlive()){
                    System.out.println("Player " + toHit.getName() + " got hit by indians! They lose a life.");
                    toHit.setHp(toHit.getHp()-1);
                }
            }
        }
        return true;
    }
}
