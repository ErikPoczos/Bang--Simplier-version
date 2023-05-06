package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Dynamite extends BlueCard{
    private final String name;
    private final String description;

    public Dynamite(){
        this.name = "Dynamite";
        this.description =  "Put a dynamite on the table. There is a 12.5% chance it will explode.\n" +
                            "The dynamite circulates around players in the opposite way of play.n\n" +
                            "The dynamite can't explode the same turn you played it";
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
    public boolean playCard(Player[] players, Player activePlayer) {
        System.out.println("You placed a dynamite on the table!");
        Dynamite dynamite = new Dynamite();
        activePlayer.placeCardOnTable(dynamite);
        return true;
    }

    public boolean checkEffect (int currentPlayerIndex, Player[] players, Card card){
        Player hasDynamite = players[currentPlayerIndex];
        Player hasNewDynamite;

        if (getChance(8)){
            System.out.println("Dynamite exploded! Player " + hasDynamite.getName() + " loses 3 lives");
            hasDynamite.setHp(hasDynamite.getHp()-3);
            hasDynamite.getCardsOnTable().remove(card);
            hasDynamite.getDeck().addToDiscarded(card);
            return true;
        } else{
            hasDynamite.getCardsOnTable().remove(card);
            int nextPlayerIndex = currentPlayerIndex;
            do {
                nextPlayerIndex--;
                if (nextPlayerIndex < 0){
                    nextPlayerIndex = players.length-1;
                }
                hasNewDynamite = players[nextPlayerIndex];
            } while (!hasNewDynamite.isAlive());

            hasNewDynamite.getCardsOnTable().add(new Dynamite());
            System.out.println("Dynamite didn't exploded! It moves to the next player: " + hasNewDynamite.getName());
            return false;
        }
    }
}
