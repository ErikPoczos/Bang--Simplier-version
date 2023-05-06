package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends BrownCard{
    private final String name;
    private final String description;

    public Beer(){
        this.name = "Beer";
        this.description = "Heal 1 life";
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
        System.out.println("You healed one life!");
        currentPlayer.setHp(currentPlayer.getHp()+1);
        return true;
    }
}