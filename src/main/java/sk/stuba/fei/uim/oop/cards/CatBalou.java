package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class CatBalou extends BrownCard{
    private final String name;
    private final String description;

    public CatBalou(){
        this.name = "Cat Balou";
        this.description = "A random card from target's hand or table will be removed.";
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
        int toRemoveIndex;
        Card toRemove;
        int place = ZKlavesnice.readInt("Do you want to remove a card from his hands (type 0) or from table (type 1)");
        while (place < 0 || place > 1){
            System.out.println("Invalid input. Try again!");
            place = ZKlavesnice.readInt("Do you want to remove a card from his hands (type 0) or from table (type 1)");
        }
        switch (place){
            case 0:
                if (toHit.getCardsInHand().size() == 0){
                    System.out.println("Player has no cards in his hands!");
                    return false;
                }
                toRemoveIndex = random.nextInt(toHit.getCardsInHand().size());
                toRemove = toHit.getCardsInHand().get(toRemoveIndex);
                System.out.println("You removed a " + toRemove.getName() + " card!" );
                toHit.removeCard(toRemove);
                break;
            case 1:
                if (toHit.getCardsOnTable().size() == 0){
                    System.out.println("Player has no cards on his table!");
                    return false;
                }
                toRemoveIndex = random.nextInt(toHit.getCardsOnTable().size());
                toRemove = toHit.getCardsOnTable().get(toRemoveIndex);
                System.out.println("You removed a " + toRemove.getName() + " card!" );
                toHit.removeCard(toRemove);
        }
        return true;
    }
}
