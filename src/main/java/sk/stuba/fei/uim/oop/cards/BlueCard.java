package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public abstract class BlueCard extends Card {
    private final boolean isBlue = true;

    @Override
    public boolean isBlue() {
        return this.isBlue;
    }

    /**Probability is 1/n. Returns true if the event happened, false if not*/
    public boolean getChance(int chance){
        return this.random.nextInt() % chance == 0;
    }

    public abstract boolean playCard(Player[] players, Player currentPlayer);
}
