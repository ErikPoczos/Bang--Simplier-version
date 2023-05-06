package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private final Player[] players;
    private int playersAlive;
    private int currentPlayerIndex;
    private final Table table;
    private Deck deck;

    public Game() {
        System.out.println("Bang! A simpler version.");
        this.deck = new Deck();
        int numberPlayers = 0;
        playersAlive = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("Enter number of players (2-4): ");
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.out.println("You entered invalid number of players. Try Again!");
            }
        }
        this.players = new Player[numberPlayers];
        for (short i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter a name for PLAYER " + (i+1) + " : "), this.deck);
            playersAlive++;
        }
        this.table = new Table();
        this.start();
    }

    private void start(){
        System.out.println("--------- GAME STARTED ---------");

        while (playersAlive > 1){
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("--- PLAYER " + currentPlayer.getName() + " STARTS TURN ---");

            if (currentPlayer.checkDynamite(currentPlayerIndex, players)){
                if (currentPlayer.getHp()<= 0){
                    checkDeadPlayers();
                    chooseNextPlayer();
                }
            }
            if (currentPlayer.checkJail(currentPlayerIndex, players)){
                this.chooseNextPlayer();
                continue;
            }

            makeTurn(currentPlayer);
            throwCards(currentPlayer);

            checkDeadPlayers();
            this.chooseNextPlayer();
        }

        showWinner();

        }

    private void makeTurn(Player currentPlayer) {
        //Draw 2 cards
        deck.drawCard(currentPlayer);
        deck.drawCard(currentPlayer);
        boolean skipTurn = false;

        while ((currentPlayer.getCardsInHand().size() > 0 && !skipTurn)){
            checkDeadPlayers();

            this.table.printTable(this.players);
            System.out.println("--------- Your (" + currentPlayer.getName() +") cards in hand ---------");
            for (int i = 0; i < currentPlayer.getCardsInHand().size();i++) {
                System.out.println("Card " + (i+1) + ": " + currentPlayer.getCardsInHand().get(i).getName());
            }

            skipTurn = this.playCard(currentPlayer.getCardsInHand(), currentPlayer);
        }
        if (currentPlayer.getCardsInHand().size() == 0){
            System.out.println("No cards to play. Your turn ends.");
        }
    }

    private boolean playCard(ArrayList<Card> cardsOnHand, Player currentPlayer) {
        boolean cardWasPlayed;
        if (playersAlive == 1){
            showWinner();
        }

        int numberCard = choosingCard(cardsOnHand);
        if (numberCard == -1){
            return true;
        }
        cardWasPlayed = cardsOnHand.get(numberCard).playCard(this.players, currentPlayer);
        //Remove played card but don't put it into discarded deck
        if (cardWasPlayed && cardsOnHand.get(numberCard).isBlue()){
            cardsOnHand.remove(numberCard);
        //Remove played card and put it into discarded deck
        } else if(cardWasPlayed && !cardsOnHand.get(numberCard).isBlue()) {
            removePlayedCard(currentPlayer, cardsOnHand.get(numberCard));
        //Card could be played
        } else {
            this.playCard(cardsOnHand, currentPlayer);
        }
        return false;
    }

    private int choosingCard(ArrayList<Card> cards) {
        int cardNumber;
        while (true) {
            cardNumber = ZKlavesnice.readInt("Enter the number of card you want to play. " +
                                                            "Enter -1 for description of cards. Enter 0 if you want to skip the round.")-1;
            if (cardNumber > cards.size()-1) {
                System.out.println("Invalid card number. Try Again!");
            } else if (cardNumber == -1) {
                System.out.println("You ended your turn!");
                return cardNumber;

            } else if (cardNumber < -1) {
                for (var card : cards){
                    System.out.println(card.getName());
                    System.out.println(card.getDescription());
                    System.out.println();
                }
            } else
                break;
        }
        return cardNumber;
    }

    private void chooseNextPlayer() {
        this.currentPlayerIndex++;
        this.currentPlayerIndex %= this.players.length;
        if (!players[currentPlayerIndex].isAlive()){
            this.chooseNextPlayer();
        }
    }

    /**Remove a card from current player's hands that was played.*/
    private void removePlayedCard(Player currentPlayer, Card card){
        currentPlayer.getCardsInHand().remove(card);
        this.deck.addToDiscarded(card);
    }

    /**Discards cards after round.*/
    private void throwCards(Player currentPlayer){
        while (currentPlayer.getCardsInHand().size() > currentPlayer.getHp()){
            currentPlayer.removeRandomCard();
        }
    }

    private void checkDeadPlayers(){
        for (int i = 0; i < players.length; i++){
            if (players[i].getHp() <= 0 && players[i].isAlive()){
                System.out.println("Player " + players[i].getName() + " died. All cards will be discarded");
                //discard all cards
                for (Iterator<Card> iterator = players[i].getCardsInHand().iterator(); iterator.hasNext();){
                    this.deck.addToDiscarded(iterator.next());
                    iterator.remove();
                }
                for (Iterator<Card> iterator = players[i].getCardsOnTable().iterator(); iterator.hasNext();){
                    this.deck.addToDiscarded(iterator.next());
                    iterator.remove();
                }
                players[i].markAsDead();
                playersAlive--;
                this.moveDeadPlayer(players, i);
            }
        }
    }

    private void showWinner(){
        for (var player : players){
            if (player.isAlive()){
                System.out.println("--- GAME FINISHED ---");
                System.out.println("The WINNER is " + players[currentPlayerIndex].getName() + "!");
                System.exit(0);
            }
        }
    }

    /**Moves a dead player at the end of players array*/
    private void moveDeadPlayer(Player[] players, int index) {
        Player temp = players[index];
        for (int i = index; i < players.length - 1; i++) {
            players[i] = players[i + 1];
        }
        players[players.length - 1] = temp;
    }
}
