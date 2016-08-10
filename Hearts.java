package card;
import java.util.ArrayList;

public class Hearts extends Game {

	private static int numberOfDecks;
	private static int numberOfPlayers;
	private static int numberOfCardsInHand;
	private static ArrayList<Hand> hand;
	private static ArrayList<Integer> scores;
	
	public Hearts(int numberOfDecks, int numberOfPlayers, int numberOfCardsInHand, ArrayList<Hand> hand){
		this.numberOfDecks=numberOfDecks;
		this.numberOfPlayers=numberOfPlayers;
		this.numberOfCardsInHand=numberOfCardsInHand;
		this.hand=hand;
		this.scores=new ArrayList<Integer>();
	}
	
	public int evaluateHand(Hand hand){
		int numOfHearts = 0;
		int Qspade = 0;
		for(int i=0; i<hand.getListOfCards().size();i++){
			if(hand.getListOfCards().get(i).getSuit() == Suit.HEART){
				numOfHearts++;
			}
			else if(hand.getListOfCards().get(i).getValue() == QUEEN && hand.getListOfCards().get(i).getSuit()==Suit.SPADE) Qspade+=this.numberOfCardsInHand;
		}
		if (numOfHearts == this.numberOfCardsInHand) {
			Qspade = -1*this.numberOfCardsInHand;
			return Qspade;
		}
		return numOfHearts+Qspade;
	}
	
	public void printScores (){
		for(int i=0; i<this.hand.size(); i++){
			this.scores.add(evaluateHand(hand.get(i)));
		}
		System.out.println(this.scores);
	}
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		
	}

}
