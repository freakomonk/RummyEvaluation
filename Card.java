package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Card implements Comparable<Card> {

	private Suit suit;
	private int value;
	private boolean isJoker;

	public Card(int value, Suit suit, boolean isJoker) {
		this.value = value;
		this.suit = suit;
		this.isJoker = isJoker;
	}

	public static ArrayList<Card> getSortedCardsByValue(ArrayList<Card> cards) {
		ArrayList<Card> sortedCards = cards;
		sortedCards.sort((Card card1, Card card2 ) -> card2.getValue() - card1.getValue());
		return sortedCards;
	}
	
	public static ArrayList<Card> getSortedCardsBySuit(ArrayList<Card> cards) {
		ArrayList<Card> sortedCards = getSortedCardsByValue(cards);
		sortedCards.sort((Card card1, Card card2 ) -> card2.getSuit().ordinal() - card1.getSuit().ordinal());
		return sortedCards;
	}
	
	public static boolean areInSequence(ArrayList<Card> cards) {
		Collections.sort(cards);
		int x = cards.get(0).value;
		for (int i = 1; i < cards.size(); i++) {
			if (Math.abs((cards.get(i).value - x)) != 1)
				return false;
			x = cards.get(i).value;
		}
		return true;
	}

	public static boolean isSameSuit(ArrayList<Card> cards) {
		Card x = cards.get(0);
		for (int i = 1; i < cards.size(); i++) {
			if (x.getSuit() != cards.get(i).getSuit()) {
				return false;
			}
		}
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
	
	public String toString() {
		String s = String.valueOf(this.value) + " " + this.suit.toString();
		return s;
	}
	public static int getSuitCount(ArrayList<Card> cards, Card card) {
		Suit suit = card.getSuit();
		return (int) cards.stream().filter(p -> p.getSuit().equals(suit)).count();
	}
	
	public static int getRankCount(ArrayList<Card> cards, Card card) {
		int value = card.getValue();
		return (int) cards.stream().filter(p -> p.getValue() == value).count();
	}
	
	public static int getSuitDistinctCount(ArrayList<Card> cards, Card card) {
		Suit suit = card.getSuit();
		return (int) cards.stream().filter(p -> p.getSuit().equals(suit)).collect(Collectors.groupingBy(p -> p.getValue())).size();
	}
	
	public static int getRankDistinctCount(ArrayList<Card> cards, Card card) {
		int value = card.getValue();
		return (int) cards.stream().filter(p -> p.getValue() == value).collect(Collectors.groupingBy(p -> p.getSuit())).size();
	}

}
