package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Table {
    public void printTable(Player[] players){
        int counter = 0;
        System.out.println("--------------------TABLE--------------------");
        for (var player : players) {
            if (player.isAlive()){
                System.out.println("Player " + counter + " : " + player.getName());
                System.out.println("Lives: " + player.getHp());
                System.out.println("Amount of cards in hand: " + player.getCardsInHand().size());
                System.out.println("Cards on table: ");
                for (Card card : player.getCardsOnTable()) {
                    printCard(card);
                }
                System.out.println();
                counter++;
            }
        }
        System.out.println("Board of dead players:");
        for (var player : players){
            if (!player.isAlive()){
                System.out.println(player.getName());
            }
        }
        System.out.println();
    }

    private void printCard(Card card){
        printLine(card);
        System.out.println();
        printName(card);
        System.out.println();
        printLine(card);
        System.out.println();
    }

    private void printLine(Card card){
        System.out.print("|");
        for (int i = 0; i < card.getName().length(); i++){
            System.out.print("-");
        }
        System.out.print("|");
    }

    private void printName(Card card){
        System.out.print("|");
        System.out.print(card.getName());
        System.out.print("|");
    }
}
