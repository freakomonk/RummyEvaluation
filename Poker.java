package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Poker extends Game {

	private static int numberOfDecks;
	private static int numberOfPlayers;
	private static int numberOfCardsInHand;
	private ArrayList<Card> table;
	String [] VALUE = {"","ROYAL FLUSH","STRAIGHT FLUSH","FOUR OF A KIND","FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIRS","ONE PAIR","HIGH HAND"};
    ArrayList<Card> hand;
	public Poker(int numberOfPlayers) {
		this.numberOfDecks = 1;
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfCardsInHand = 2;
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		
		int score = evaluate(hand);
		

	}

	public static int evaluate(ArrayList<Card> hand) {
		HashMap<Suit, Integer> suit = Card.getFreqOfSuits(hand);
		HashMap<Integer, Integer> vals = Card.getFreqOfCards(hand);
		int pairs = 0;
		int triples = 0;
		int fours = 0;
		int fives = 0;
		
		Iterator it = (Iterator) vals.entrySet().iterator();
		while (it.hasNext()) 
		{
			Map.Entry pair = (Map.Entry) it.next();
			if ((Integer) pair.getValue() == 4) {
				fours++;
				//triples--;
				//pairs--;
			}
			if ((Integer) pair.getValue() == 3) {
				triples++;
				//pairs--;
			}
			if ((Integer) pair.getValue() == 2) {
				pairs++;
			}
		}

		Collections.sort(hand);
		if (Card.isSameSuit(hand) && Card.areInSequence(hand)) {
			if (hand.get(0).getValue() == 14)
				return 1;
			else
				return 2;

		} else if (Card.isSameSuit(hand))
			return 5;
		else if (Card.areInSequence(hand))
			return 6;
		else if (fours == 1)
			return 3;
		else if (triples == 1 && pairs == 1)
			return 4;
		else if (triples == 1)
			return 7;
		else if (pairs == 2)
			return 8;
		else if (pairs == 1)
			return 9;
		else
			return 10;

	}
	public static void main(String [] args)
	{
		Card a = new Card(1, Suit.DIAMOND);
		Card b = new Card(2, Suit.DIAMOND);
		Card c = new Card(3, Suit.DIAMOND);
		Card d = new Card(6, Suit.DIAMOND);
		Card e = new Card(5, Suit.DIAMOND);
		

		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(a);
		hand.add(b);
		hand.add(c);
		hand.add(d);
		hand.add(e);
		
		int res = evaluate(hand);
		System.out.println(res);
			
	}

}
