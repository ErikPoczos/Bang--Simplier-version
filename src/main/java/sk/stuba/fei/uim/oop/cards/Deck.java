package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.*;

public class Deck {
    private int totalDrawableCards = 71;
    private final List<Card> deck;
    private final List<Card> discardedDeck;

    public Deck(){
        this.discardedDeck = new ArrayList<>();
        this.deck = new ArrayList<>();
        HashMap<Card, Integer> cardTypes = new HashMap<>();
        //Brown cards
        cardTypes.put(new Bang(), 30);
        cardTypes.put(new Miss(), 15);
        cardTypes.put(new Beer(), 8);
        cardTypes.put(new CatBalou(), 6);
        cardTypes.put(new Stagecoach(), 4);
        cardTypes.put(new Indians(), 2);
        //Blue cards
        cardTypes.put(new Barrel(), 2);
        cardTypes.put(new Dynamite(), 1);
        cardTypes.put(new Jail(), 3);

        for (Map.Entry<Card, Integer> entry : cardTypes.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                deck.add(entry.getKey());
            }
        }
        Collections.shuffle(this.deck);
        }

    public int getTotalDrawableCards() {
        return totalDrawableCards;
    }

    public List<Card> getDiscardedDeck() {
        return discardedDeck;
    }

    public void drawCard(Player toDraw){
        if (totalDrawableCards == 0){
            if (discardedDeck.size() != 0){
                System.out.println("No cards left. Let me shuffle the discarded deck...");
                deck.addAll(discardedDeck);
                Collections.shuffle(deck);
                totalDrawableCards = deck.size();
                discardedDeck.clear();
            } else {
                System.out.println("There arent any cards to draw! So you won't get any cards...");
                return;
            }
        }

        toDraw.getCardsInHand().add(deck.get(0));
        deck.remove(0);
        totalDrawableCards--;
    }

    public void addToDiscarded(Card card){
        this.discardedDeck.add(card);
    }
}
