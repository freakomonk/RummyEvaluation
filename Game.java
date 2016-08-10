package card;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
	
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
	
	private static int numberOfDecks;
	private static int numberOfPlayers;
	private static int numberOfCardsInHand;
	private List<Integer> scores;
	private List<ArrayList<Card>> hand;
	
	public abstract void calculate();
	
}
