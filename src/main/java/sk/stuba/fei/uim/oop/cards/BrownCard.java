package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public abstract class BrownCard extends Card{
    private final boolean isBlue = false;

    @Override
    public boolean isBlue() {
        return this.isBlue;
    }

    public abstract boolean playCard(Player[] players, Player currentPlayer);
}
