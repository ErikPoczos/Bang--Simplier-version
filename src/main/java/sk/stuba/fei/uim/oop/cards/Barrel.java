package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends BlueCard {
    private final String name;
    private final String description;

    public Barrel(){
        this.name = "Barrel";
        this.description =  "Hide yourself from enemy Bang card. There is a 25% chance it will be effective.\n" +
                            "If Barrel fails, a Miss card will be used, if you have one.";
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
        for (var card : currentPlayer.getCardsOnTable()){
            if (card instanceof Barrel){
                System.out.println("You cannot have more then 1 barrel in front of you!");
                return false;
            }
        }
        System.out.println("You placed a barrel on your table!");
        Barrel barrel = new Barrel();
        currentPlayer.placeCardOnTable(barrel);
        return true;
    }
}
