package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Card implements Comparable<Card> {

	private Suit suit;
	private int value;
	private boolean isJoker;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	public static boolean areInSequence(ArrayList<Card> cards) {
		Collections.sort(cards);
		int flag = 0;
		int x = cards.get(0).value;

		for (int i = 1; i < cards.size(); i++) {
			if (Math.abs((cards.get(i).value - x)) != 1)
				flag = 1;
			x = cards.get(i).value;
		}

		if (flag == 1)
			return false;

		return true;
	}

	public static boolean isSameSuit(ArrayList<Card> cards) {
		int flag = 0;
		Card x = cards.get(0);

		for (int i = 1; i < cards.size(); i++) {

			if (x.getSuit() != cards.get(i).getSuit()) {
				flag = 1;
			}
		}

		if (flag == 1)
			return false;

		return true;
	}

	public static HashMap<Integer, Integer> getFreqOfCards(ArrayList<Card> cards) {

		HashMap<Integer, Integer> cardFreq = new HashMap<Integer, Integer>();

		for (Card c : cards) {

			if (cardFreq.containsKey(c.value)) 
				cardFreq.put(c.value, cardFreq.get(c.value) + 1);
			else 
				cardFreq.put(c.value, 1);
		}

		return cardFreq;
	}

	public static HashMap<Suit, Integer> getFreqOfSuits(ArrayList<Card> cards) {
		HashMap<Suit, Integer> suitFreq = new HashMap<Suit,Integer>();

		for (Card c : cards) {
			if (suitFreq.containsKey(c.getSuit()))
				suitFreq.put(c.getSuit(), suitFreq.get(c.getSuit()) + 1);
			else
				suitFreq.put(c.getSuit(), 1);
		}

		return suitFreq;
	}

	@Override
	public int compareTo(Card card) {
		return card.value - this.value;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isJoker() {
		return isJoker;
	}

	public void setJoker(boolean isJoker) {
		this.isJoker = isJoker;
	}

	public static void main(String[] args) {
		Card a = new Card(1, Suit.HEART);
		Card d = new Card(3, Suit.DIAMOND);
		Card b = new Card(2, Suit.DIAMOND);

		ArrayList<Card> c = new ArrayList<Card>();
		c.add(a);
		c.add(b);
		c.add(d);
		System.out.println(Card.areInSequence(c));
	}
}
