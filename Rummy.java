package card;

import java.util.ArrayList;

public class Rummy {

	private static int numberOfDecks;
	private static int numberOfPlayers;
	private static int numberOfCardsInHand;
	private static int ONE = 1;
	private static int TWO = 2;
	private static int THREE = 3;
	private static int FOUR = 4;
	private static int FIVE = 5;
	private static int SIX = 6;
	private static int SEVEN = 7;
	private static int EIGHT = 8;
	private static int NINE = 9;
	private static int TEN = 10;
	private static int JACK = 11;
	private static int QUEEN = 12;
	private static int KING = 13;
	private static int SEQ_LEN = 3;

	private ArrayList<Card> cards;

	public Rummy(int numberOfDecks, int numberOfPlayers, int numberOfCardsInHand) {
		this.numberOfDecks = numberOfDecks;
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfCardsInHand = numberOfCardsInHand;
		this.cards = new ArrayList<>();
	}

	public void addHand(Card hand) {
		this.cards.add(hand);
	}

	public ArrayList<Card> getHands() {
		return this.cards;
	}

	public Integer calculate(ArrayList<Card> cards) {
		int score = 0;
		boolean hasJoker = false;
		ArrayList<Card> sequenceCards = getSequence(cards, hasJoker);
		if (sequenceCards != null) {
			for (Card card : sequenceCards) {
				System.out.print(card.getValue() + " ");
			}
			System.out.println();
		} else {
			System.out.println("No sequence available");
		}
		return score;
	}

	private boolean isConsecutive(int prevVal, Suit prevSuit, Card card) {
		return (prevSuit == null) || (prevVal == -1)
				|| (prevSuit.equals(card.getSuit()) && Math.abs(prevVal - card.getValue()) == 1);
	}

	public ArrayList<Card> getSequence(ArrayList<Card> cards, boolean hasJoker) {
		ArrayList<Card> sortedCards = Card.getSortedCardsByValue(cards);
		int req_size = SEQ_LEN;
		int count = 0;
		int prevVal = -1;
		Suit prevSuit = null;
		ArrayList<Card> selectedCards = new ArrayList<>();
		for (Card card : sortedCards) {
			// System.out.println(card.getValue() + " " +
			// card.getSuit().name());
			// System.out.println(count);
			if (isConsecutive(prevVal, prevSuit, card) || hasJoker) {
				count++;
				if (hasJoker)
					hasJoker = false;
				else
					selectedCards.add(card);
				if (count == req_size)
					return selectedCards;
			} else {
				selectedCards.clear();
				count = 0;
			}
			prevVal = card.getValue();
			prevSuit = card.getSuit();
		}
		return null;
	}

	public boolean isSequence(ArrayList<Card> cards, int start, int end) {
		ArrayList<Card> subCards = (ArrayList<Card>) cards.subList(start, end);
		return Card.areInSequence(subCards) && Card.isSameSuit(subCards);
	}

	public boolean isSet(ArrayList<Card> cards, int start, int end) {
		ArrayList<Card> subCards = (ArrayList<Card>) cards.subList(start, end);
		return Card.isSameSuit(subCards);
	}
}
