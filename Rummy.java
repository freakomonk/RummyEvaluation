package card;

import java.util.ArrayList;

public class Rummy extends Game {

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
	
	private ArrayList<Hand> hands;
	
	public Rummy(int numberOfDecks, int numberOfPlayers, int numberOfCardsInHand){
		this.numberOfDecks=numberOfDecks;
		this.numberOfPlayers=numberOfPlayers;
		this.numberOfCardsInHand=numberOfCardsInHand;
		this.hands = new ArrayList<>();
	}
	
	public void addHand(Hand hand) {
		this.hands.add(hand);
	}
	
	public ArrayList<Hand> getHands() {
		return this.hands;
	}
	
	public ArrayList<Integer> getScores() {
		ArrayList<Integer> scores =  new ArrayList<>();
		for(Hand hand: this.hands) {
			scores.add(calculate(hand));
		}
		return scores;
	}


	public Integer calculate(Hand hand) {
		int score = 0;
		boolean hasJoker = hand.hasJoker();
		ArrayList<Card> sequenceCards = getSequence(hand, hasJoker);
		if(sequenceCards != null) {
			for(Card card: sequenceCards) {
				System.out.print(card.getValue() + " ");
			}
			System.out.println();
		} else {
			System.out.println("No sequence available");
		}
		return score;
	}
	
	private boolean isConsecutive(int prevVal, Suit prevSuit, Card card ) {
		return (prevSuit == null) || (prevVal == -1) || (prevSuit.equals(card.getSuit()) && Math.abs(prevVal - card.getValue()) == 1 );	
	}
	
	public ArrayList<Card> getSequence(Hand hand, boolean hasJoker) {
		ArrayList<Card> sortedCards = hand.getSortedCards();
		int req_size = SEQ_LEN;
		int count = 0;
		int prevVal = -1;
		Suit prevSuit = null;
		ArrayList<Card> selectedCards = new ArrayList<>();
		for(Card card: sortedCards) {
//			System.out.println(card.getValue() + " " + card.getSuit().name());
//			System.out.println(count);
			if(isConsecutive(prevVal, prevSuit, card) || hasJoker) {
				count++;
				if(hasJoker) hasJoker = false;
				else selectedCards.add(card);
				if(count == req_size) return selectedCards;
			} else {
				selectedCards.clear();
				count = 0;
			}
			prevVal = card.getValue();
			prevSuit = card.getSuit();
		}
		return null;
	}
}
