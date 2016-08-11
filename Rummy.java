package card;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Rummy {

	private static int numberOfDecks;
	private static int numberOfPlayers;
	private static int numberOfCardsInHand;
	private static int ACE = 13;
	private static int THREE = 3;
	private static int FOUR = 4;
	private static int FIVE = 5;
	private static Integer[] seqLengths = {THREE, FOUR, FIVE};
	
	private ArrayList<Card> cards;
	private int jokerCard;

	public Rummy(int numberOfDecks, int numberOfPlayers, ArrayList<Card> cards, int jokerCard) {
		this.numberOfPlayers = numberOfPlayers;
		this.jokerCard = jokerCard;
		this.numberOfCardsInHand = cards.size();
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	public void evaluate() {
		int numJokers = (int)this.cards.stream().filter(p -> p.getValue() == jokerCard || p.isJoker()).count();
		System.out.println("Jokers " + jokerCard + " "  + numJokers);
		ArrayList<Card> finalCards = getNonJokerCards();
		ArrayList<Card> sortedCards = Card.getSortedCardsBySuit(finalCards);
		calculateSequences(sortedCards, numJokers);
	}
	
	private void calculateSequences(ArrayList<Card> cards, int numJokers) {
		for(int len : seqLengths) {
			System.out.println("seq length : " + len);
			for(Suit suit: Suit.values()) {
				System.out.println(suit);
				System.out.println(getNoOfSequences(cards, suit, len, numJokers));
			}
		}
	}
	
	private ArrayList<Card> getNonJokerCards() {
		return (ArrayList<Card>) this.cards.stream().filter(p -> p.getValue() != jokerCard && !p.isJoker()).collect(Collectors.toList());
	}
	
	private int getNoOfSequences(ArrayList<Card> sortedCards, Suit suit, int len, int numJokers) {
		ArrayList<Card> suitCards = (ArrayList<Card>) sortedCards.stream().filter(p -> p.getSuit().equals(suit)).collect(Collectors.toList());
		int count = 0;
		if(suitCards.size() >= len) {
			int start = 0;
			while(start + len - 1 < suitCards.size()) {
				int gap = getGap(suitCards, start, len - 1);
				System.out.println(suitCards.subList(start, start+len).toString() + " " + gap);
				if(gap == 0 || gap <=  numJokers) count++;
				start++;
			}
		}
		return count;
	}

	public int getGap(ArrayList<Card> cards, int start, int size) {
		int i = start, count = 0;
		while(i < start+size) {
			int diff = cards.get(i).getValue() - cards.get(i+1).getValue() - 1;
			if(diff == 0) count ++;
			i++;
		}
		return size - count;
	}
	
	
	public int getSuitDistinctCount(Suit suit) {
		return (int) cards.stream().filter(p -> p.getSuit().equals(suit)).collect(Collectors.groupingBy(p -> p.getValue())).size();
	}
	
	public int getRankDistinctCount(int value) {
		return (int) cards.stream().filter(p -> p.getValue() == value).collect(Collectors.groupingBy(p -> p.getSuit())).size();
	}
}
