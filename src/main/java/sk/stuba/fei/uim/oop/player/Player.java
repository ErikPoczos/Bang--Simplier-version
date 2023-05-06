package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Deck;
import sk.stuba.fei.uim.oop.cards.Dynamite;
import sk.stuba.fei.uim.oop.cards.Jail;

import java.util.ArrayList;

public class Player {
    private int hp;
    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> cardsOnTable;
    private final String name;
    private boolean isAlive;
    private Deck deck;

    //getters
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    public int getHp() {
        return hp;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Card> getCardsOnTable() {
        return cardsOnTable;
    }
    public Deck getDeck() {
        return deck;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Player(String name, Deck deck){
        this.deck = deck;
        isAlive = true;
        cardsInHand = new ArrayList<>();
        cardsOnTable = new ArrayList<>();
        this.hp = 4;
        this.name = name;
        for (short i = 0; i < 4; i++){
            deck.drawCard(this);
        }
    }

    public void placeCardOnTable(Card card){
        this.cardsOnTable.add(card);
    }

    /**Removes another player's card.*/
    public void removeCard(Card card){
        if (card.isBlue()) {
            this.cardsOnTable.remove(card);
            this.deck.addToDiscarded(card);
        } else {
            this.cardsInHand.remove(card);
            this.deck.addToDiscarded(card);
        }
    }

    public void removeRandomCard(){
        int index = cardsInHand.get(0).random.nextInt(this.cardsInHand.size());
        System.out.println("Card " + cardsInHand.get(index).getName() + " was removed!");
        this.deck.addToDiscarded(cardsInHand.get(index));
        this.cardsInHand.remove(index);
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void markAsDead(){
        this.isAlive = false;
    }

    public boolean checkDynamite(int activePlayer, Player[] players){
        for (var card : this.cardsOnTable) {
            if (card instanceof Dynamite) {
                return ((Dynamite) card).checkEffect(activePlayer, players, card);
            }
        }
        return false;
    }

    public boolean checkJail(int activePlayer, Player[] players){
        for (var card : this.cardsOnTable){
            if (card instanceof Jail){
                return ((Jail) card).checkEffect(activePlayer, players, card);
            }
        }
        return false;
    }
}
