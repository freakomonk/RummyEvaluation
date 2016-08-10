package card;

import java.util.List;

public class Pack {

	private List<Card> cards;
	int numberOfCards;
	boolean isJoker;

	public Pack(int numberOfCards, boolean isJoker) {
		this.numberOfCards = numberOfCards;
		this.isJoker = isJoker;
	}
}

