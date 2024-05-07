import java.io.*;
import java.util.*;

class Card {
    int count;
    int type; //for error fixing
    long buyPrice;
    long sellPrice;
    long rankingFactor; // Affects buying and Selling

    public Card(int count, long buyPrice, long sellPrice) {
        this.count = count;
        this.type = type;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        if (count == 0) {
            this.rankingFactor = 2 * buyPrice;
        } else if (count == 1) {
            this.rankingFactor = buyPrice + sellPrice;
        } else {
            this.rankingFactor = 2 * sellPrice;
        }
    }

    public static Card[] readCards(int N, int T, BufferedReader input) throws IOException {
        Card[] cards = new Card[T + 1];
        for (int i = 0; i <= T; i++) {
            cards[i] = new Card(0, 0, 0);
        }

        String[] cardTypes = input.readLine().split(" ");
        for (String cardType : cardTypes) {
            int type = Integer.parseInt(cardType);
            cards[type].count++;
        }

        for (int i = 1; i <= T; i++) {
            String[] prices = input.readLine().split(" ");
            long buyPrice = Long.parseLong(prices[0]);
            long sellPrice = Long.parseLong(prices[1]);
            cards[i].buyPrice = buyPrice;
            cards[i].sellPrice = sellPrice;
            cards[i].type = i; //for error fixing
            // Update rankingFactor for each card
            if (cards[i].count == 0) {
                cards[i].rankingFactor = 2 * buyPrice;
            } else if (cards[i].count == 1) {
                cards[i].rankingFactor = buyPrice + sellPrice;
            } else {
                cards[i].rankingFactor = 2 * sellPrice;
            }
        }

        return cards;
    }
}


public class cardtrading {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = input.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]); //no of cards
        int T = Integer.parseInt(firstLine[1]); //no of types
        int K = Integer.parseInt(firstLine[2]); //no of combos

        Card[] cards = Card.readCards(N, T, input);
        input.close();

        // Update the comparators to handle long values
        PriorityQueue<Card> buyQueue = new PriorityQueue<>(Comparator.comparingLong(card -> card.rankingFactor));

        // Add cards to the PriorityQueue
        for (Card card : cards) {
            if (card.buyPrice != 0) {  // Check to ensure its not the empty card at the start
                buyQueue.add(card);
            }
        }

        long profit = 0;
        
        /*** Buying and sellin
         * this helps to ensure that the total number of combo is met before selling
         * this also 
         ***/
        for (int i = 0; i < T; i++) {
            Card card = buyQueue.poll();
            if (i < K) { 
                //System.out.println("buying card" + card.type + "at price of" + card.buyPrice); //fixing error
                profit -= (2 - card.count) * card.buyPrice;
            } else { // Sell
                //System.out.println("selling card" + card.type + "at price of" + card.sellPrice); //fixing error
                profit += card.count * card.sellPrice;
            }
        }        
        
        System.out.println(profit);
    }
}

