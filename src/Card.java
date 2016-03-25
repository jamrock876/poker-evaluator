import java.util.*;

public class Card implements Comparable<Card>{
		
	private String suit[] = {"Clubs", "Diamonds", "Hearts", "Spades"}; // use integers 1-4 to encode the suit
	private String value[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; // use integers 1-13 to encode the value
	private int su;
	private int val; 

    // Constructor to create a new card by ppassing its suit and value
    // This is passed with index from 0-3 & 0-12
	public Card(int s, int v){
		this.su = s;
		this.val = v;
    }

    // Getter for card's value
	public int getValue(){
		return this.val;
	}

    // Getter for card's suit
	public int getSuit(){
		return this.su;
	}

    // Implement Comparable, allowing cards to be compared for equality
	public int compareTo(Card c){
		if(suit[this.su] == suit[c.su] && value[this.val] == value[c.val]){
			return 0;
		}else{
			return -1;
		}	
	}

    // Allows function to be printable in a user friendly format
	public String toString(){
		return ("("+value[this.val]+" of "+suit[this.su]+")");	
	}

    // Unit test for Card class
	public static void main(String[] args){
		int result;
		Card red = new Card(4,5);
		Card blue = new Card(4,9);
		Card orange = new Card(4,9);
		System.out.println("This is to test the comparable method");
		result = orange.compareTo(red);
		System.out.println(result);
		result = orange.compareTo(blue);
		System.out.println(result);
		System.out.println(red.toString());
	}

}
