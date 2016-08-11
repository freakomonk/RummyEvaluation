package card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private int jokerCard;

	public Rummy(int numberOfDecks, int numberOfPlayers, ArrayList<Card> cards, int jokerCard) {
		this.numberOfDecks = numberOfDecks;
		this.numberOfPlayers = numberOfPlayers;
		this.jokerCard = jokerCard;
		this.numberOfCardsInHand = cards.size();
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	public Integer calculate() {
		List<Card> nonJokercards = this.cards.stream().filter(p -> p.getValue() != jokerCard).collect(Collectors.toList());
		int score = 0;
		int numJokers = (int)this.cards.stream().filter(p -> p.getValue() == jokerCard).count();
		ArrayList<Card> finalCards = (ArrayList<Card>) nonJokercards;
		ArrayList<Card> sortedCards = Card.getSortedCardsBySuit(cards);
		for(int len=SEQ_LEN; len<=SEQ_LEN+2; len++) {
			System.out.println("Sequences of length " + len);
			ArrayList<ArrayList<Card>> sequences = getSuitSequences(finalCards, numJokers, len);
			for(ArrayList<Card> sequenceCards : sequences) {
				printSequence(sequenceCards);
			}
		}
		return score;
	}

	private void printSequence(ArrayList<Card> sequenceCards) {
		if(sequenceCards != null) {
			for (Card card : sequenceCards) {
				System.out.print(card.getValue() + " ");
			}
			System.out.println(sequenceCards.get(0).getSuit());
		} else {
			System.out.println("No sequence available");
		}
	}
	
	private boolean isConsecutive(int prevVal, Suit prevSuit, Card card) {
		return (prevSuit == null) || (prevVal == -1)
				|| (prevSuit.equals(card.getSuit()) && Math.abs(prevVal - card.getValue()) == 1);
	}

	public ArrayList<ArrayList<Card>> getSuitSequences(ArrayList<Card> cards, int numJokers, int size) {
		ArrayList<ArrayList<Card>> seqCards = new ArrayList<ArrayList<Card>> ();
		ArrayList<Card> sortedCards = Card.getSortedCardsBySuit(cards);
		int prevVal = -1;
		Suit prevSuit = null;
		ArrayList<Card> selectedCards = new ArrayList<>();
		Card joker = new Card(-1, null);
		for(Suit suit: Suit.values()) {
			ArrayList<Card> suitCards = (ArrayList<Card>) sortedCards.stream().filter(p -> p.getSuit().equals(suit)).collect(Collectors.toList());
			for(Card card: suitCards) {
//			 System.out.println(card.getValue() + " " + card.getSuit().name());
				if (isConsecutive(prevVal, prevSuit, card)) {
					selectedCards.add(card);
				} else if(numJokers > 0){
					numJokers--;
					selectedCards.add(joker);				
				} else if(card.isJoker()) {
					selectedCards.add(card);			
				} else {
					selectedCards.clear();
					selectedCards.add(card);
				}
				if (selectedCards.size() == size) {
					seqCards.add((ArrayList<Card>) selectedCards.clone());
					selectedCards.clear();
				}
				prevVal = card.getValue();
				prevSuit = card.getSuit();
			}
		}
		return seqCards;
	}

	public int getCount(Card card) {
		int value = card.getValue();
		return (int) cards.stream().filter(p -> p.getValue() == value).collect(Collectors.groupingBy(p -> p.getSuit())).size();
	}
}
