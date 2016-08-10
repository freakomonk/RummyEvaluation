package card;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	private static int MIN_LIMIT = 1;
	private static int MAX_LIMIT = 13;
	private static int NO_OF_PLAYERS = 4;
	
	public static void main(String[] args) {
		int noOfCards = MAX_LIMIT - MIN_LIMIT + 1;
		ArrayList<Hand> hands = new ArrayList<>();
		for(int player=1; player<=NO_OF_PLAYERS; player++) {
			Hand hand = new Hand(noOfCards);
	 		for(int value=MIN_LIMIT; value<MAX_LIMIT; value++) {
				for(Suit suit : Suit.values()) {
					Card card = new Card(value, suit);
					hand.addCard(card);
				}
			}
	 		hands.add(hand);
		}
		for(Hand hand: hands) {
//			System.out.println(hand.getListOfCards().size());
			for(Card card : hand.getListOfCards()) {
				System.out.print(card.getValue() +  " ");
			}
			System.out.println();
		}
		System.out.println();
		for(Hand hand: hands) {
			for(Card card : hand.getSortedCards()) {
				System.out.print(card.getValue() +  " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
