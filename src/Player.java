import java.util.*;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<Card>(5); 
	private int tokens = 5;
		
	public Player(){		
		this.tokens--;
	}

	// add the card c to the player's hand	
	public void addCard(Card c){
			hand.add(c);
	}

	// remove the card c from the player's hand
	public void removeCard(Card c){
			hand.remove(c);	
	}
			
	public void addPayout(int p){
		this.tokens = this.tokens + p;
	}

	public int getTokens(){
		return this.tokens;
	}

	public ArrayList<Card> getHand(){
		return hand;
	}

	public String toString(){
		return (""+hand);
	}

}


