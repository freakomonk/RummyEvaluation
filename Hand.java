package card;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	private ArrayList<Card> cards;
	private int noOfCards;
	
	public Hand(int noOfCards) {
		this.noOfCards = noOfCards;
		this.cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public ArrayList<Card> getSortedCards() {
		ArrayList<Card> sortedCards = this.cards;
		Collections.sort(sortedCards);
		return sortedCards;
	}
	
	public ArrayList<Card> getListOfCards(){
		return cards;
	}
}
