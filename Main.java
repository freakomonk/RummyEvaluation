package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

	private static int MIN_LIMIT = 1;
	private static int MAX_LIMIT = 13;
	private static int NO_OF_SUITS = 4;
	
	public static void main(String[] args) {
		ArrayList<Card> cards = new ArrayList<>();
		int noOfCards = MAX_LIMIT - MIN_LIMIT + 1;
 		for(int value=MIN_LIMIT; value<MAX_LIMIT; value++) {
			for(Suit suit : Suit.values()) {
				Card card = new Card(value, suit);
				cards.add(card);
			}
		}
		Collections.shuffle(cards);
		Random rnd = new Random();
		ArrayList<Card> rummyCards = new ArrayList<>(); 
		for(int value=MIN_LIMIT; value<MAX_LIMIT; value++) {
			rummyCards.add(cards.remove(rnd.nextInt(cards.size())));
		}
		
		for(Card card: cards) {
			System.out.print(card.getValue() + " " + card.getSuit()+"|");
		}
		System.out.println();
		ArrayList<Card> newCards = new ArrayList<>();
		newCards = Card.getSortedCardsByValue(rummyCards);
		for(Card card: newCards) {
			System.out.print(card.getValue() + " " + card.getSuit()+"|");
		}
		System.out.println();
		cards = Card.getSortedCardsBySuit(rummyCards);
		for(Card card: cards) {
			System.out.print(card.getValue() + " " + card.getSuit()+"|");
		}
		System.out.println();
//		Rummy rummy = new Rummy(1, 1, rummyCards);
//		rummy.calculate();
			
	}
}
