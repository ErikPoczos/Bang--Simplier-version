package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends BrownCard {
    private final String name;
    private final String description;

    public Bang(){
        this.name = "Bang";
        this.description =  "Choose a target and shoot them, taking 1 life. Beware that if they have a Miss card they will dodge your attack.\n" +
                            "If they have a barrel in front of them there is 25% chance they dodge it";
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
        Player toHit = choosePlayer(players, currentPlayer);

        //Check barrel first
        for (var card : toHit.getCardsOnTable()){
            if (card instanceof Barrel){
                if (((Barrel) card).getChance(4)){
                    System.out.println("Your target ducked into a barrel, therefore you missed!");
                    return true;
                }
            }
        }

        //Check miss if barrel didn't work
        for (var card : toHit.getCardsInHand()){
            if (card instanceof Miss){
                System.out.println("Your target used a MISS card, therefore you missed!");
                toHit.removeCard(card);
                return true;
            }
        }

        toHit.setHp(toHit.getHp()-1);
        System.out.println("You hit your target: " + toHit.getName());
        return true;
    }
}

