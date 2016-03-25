import java.util.*;

public class Deck {
	
	Random rand = new Random();
	private Card[] theDeck = new Card[52];
	private Card[] shuffledDeck = new Card[52];
	private int top = -1; 
	// the index of the top of the deck
		

		
	// Make a 52 card deck
	public Deck(){
		int count = 0;
		for (int a=0; a<4; a++){
			for(int b=0; b<13; b++){
				theDeck[count] = new Card(a, b);	//sets the array with all 52 cards using a card constructor
				count++;
			}	
		} 
	}
		
	// Shuffle the deck here
	public void shuffle(){
		int r;
		Card temp;

		for(int i=51;i>0;i--){
			r = rand.nextInt(i);
			temp = theDeck[i];
			theDeck[i] = theDeck[r];
			theDeck[r] = temp;
		}
		
	}
		
	// Deal the top card in the deck
	public Card deal(){
			top ++;
			return theDeck[top];
	}
		
	//public Card toCard(){

		//}

	// Unit Test for Deck class
	public static void main(String[] args){
		Deck d = new Deck();
		for (int x=0;x<52;x++){
			System.out.print(d.theDeck[x]);
		}
		d.shuffle();
		System.out.println("\n\nAfter the deck is shuffled");
		System.out.println();
		System.out.println();
		for (int x=0;x<52;x++){
			System.out.print(d.theDeck[x]);
		}
		System.out.println();
		System.out.println("\nAlways dealing out the top cards");
		System.out.println(d.deal());
		System.out.println(d.deal());
		System.out.println(d.deal());
		System.out.println(d.deal());
	}

}
