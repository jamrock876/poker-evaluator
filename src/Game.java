import java.util.*;

public class Game {
	
	
	private Player human;
	private Deck cards;
	private Card specificCard;
	Scanner input = new Scanner(System.in);
		
					
	
	public Game(String[] testHand){
		//ArrayList<Card> test_hand = new ArrayList<Card>(5); 
		int val;
		human = new Player();
		for (int i=0;i<5;i++){
			if (testHand[i].contains("c")){
				val = Integer.parseInt(testHand[i].substring(1));
				specificCard = new Card(0,val);
				human.addCard(specificCard);
			}
			if (testHand[i].contains("d")){
				val = Integer.parseInt(testHand[i].substring(1));
				specificCard = new Card(1,val);
				human.addCard(specificCard);
			}
			if (testHand[i].contains("h")){
				val = Integer.parseInt(testHand[i].substring(1));
				specificCard = new Card(2,val);
				human.addCard(specificCard);
			}
			if (testHand[i].contains("s")){
				val = Integer.parseInt(testHand[i].substring(1));
				specificCard = new Card(3,val);
				human.addCard(specificCard);
			}
		}		
	}
		
	// This constructor is to actually play a normal game	
	public Game(){
		 human = new Player();
		 cards = new Deck();
		 cards.shuffle();
		 for(int j=0;j<5;j++){
		 	human.addCard(cards.deal());
		 }
	}
		
	// this method should play the game		
	public void play(){
			System.out.println("You have "+human.getTokens()+" coins remaining.");
			System.out.println("your cards are: "+ human.toString());
			System.out.println("what would you like to do?");
			System.out.println("1 - swap some cards.");
			System.out.println("2 - swap ALL cards.");
			System.out.println("3 - stay.");
			int option;
			option = input.nextInt();
			switch(option){
				case 1: swapSome();
					break;
				case 2: swapAll();
					break;
				case 3: checkHand(human.getHand());
					break;
				default: System.out.println("Please enter a valid option!!\n");
						play();
			}
		
	}
		
	public void swapSome(){
		int change;
		System.out.println("how many cards yould you like to swap?");
		int i = input.nextInt();
		for (int j=0;j<i;j++) {
			System.out.println("Enter the index of the card you would like to change(0-4)");
			//neeeed to checck it wrong index is entered
				change = input.nextInt();
				Card get_rid = human.getHand().get(change);
				human.removeCard(get_rid);
				human.addCard(cards.deal());
				System.out.println("your new hand is: "+human.toString());
		}
		System.out.println("\n\nhmmmm");
		checkHand(human.getHand());
	}

	public void swapAll(){
		for(int j=4;j>=0;j--){
		 	human.removeCard(human.getHand().get(j));
		 }
		for(int i=0;i<5;i++){
		 	human.addCard(cards.deal());
		 }
		System.out.println("your new hand is: "+human.toString());
		System.out.println();
		checkHand(human.getHand());
	}
		
	public String checkHand(ArrayList<Card> hand){
			if (isRoyalFlush(hand)){
				System.out.println("you have  a royal flush!");
			}
			else if (isStriaghtFlush(hand)){
				System.out.println("you have a straight flush!");
			}
			else if (intermediatetest(hand, "isFourOfAKind")){
				System.out.println("you have a four of a kind!");
			}
			else if (intermediatetest(hand, "isFullHouse")){
				System.out.println("you have a full house!");
			}
			else if (isFlush(hand)){
				System.out.println("you have a flush");
			}
			else if(isStriaght(hand)){
				System.out.println("you have a straight!");
			}
			else if (intermediatetest(hand, "isThreeOfAKind")){
				System.out.println("you have a three of a kind");
			}
			else if (intermediatetest(hand, "isTwoPair")){
				System.out.println("you have a two pair!");
			}
			else if (intermediatetest(hand, "isOnePair")){
				System.out.println("you have a pair");
			}

			return ("done");

	}

    //COMPLETED
	public boolean isStriaght(ArrayList<Card> hand){
		int tempValue = hand.get(0).getValue();
		Card temp;
		//sort arraylist
		for(int i=1;i<4;i++){
			if (tempValue>hand.get(i).getValue()){
				temp = hand.get(i-1);
				hand.remove(i-1);
				hand.add(i, temp);
			}
		}
		//straight 
		int value = hand.get(0).getValue();
		for(int j =0;j<4;j++){
			if(((hand.get(j).getValue())+1) != hand.get(j+1).getValue()){
				return false;
			}
		}
		return true;
	}

    //COMPLETED
	public boolean isStriaghtFlush(ArrayList<Card> hand){
		if ((isStriaght(hand)) && (isFlush(hand))){
			return true;
		}
		return false;
	}

    //COMPLETED
	public boolean isRoyalFlush(ArrayList<Card> hand){
        //does extra get of the same first variable
		int suit = hand.get(0).getSuit();
		Hashtable<Integer, Card> royalflush = new Hashtable<>();
	 	for (int i = 0; i < 5; i++){
			if ((suit == (hand.get(i).getSuit())) && (hand.get(i).getValue() == 9 && !royalflush.containsKey(9) || hand.get(i).getValue() == 10 && !royalflush.containsKey(10) || hand.get(i).getValue() == 11 && !royalflush.containsKey(11) || hand.get(i).getValue() == 12 && !royalflush.containsKey(12) || hand.get(i).getValue() == 0 && !royalflush.containsKey(0))) {
                royalflush.put(hand.get(i).getValue(), new Card(hand.get(i).getSuit(), hand.get(i).getValue()));
			}
		}

        if (royalflush.size() == 5){
            return true;
        }else {
            return false;
        }
	}

    //COMPLETED used to solve 3 of a kind, full house and 2 of a kind
	public boolean intermediatetest(ArrayList<Card> hand, String test) {
        int [] bucket = new int[13];
        for(int i=0;i<bucket.length;i++){
            bucket[i] = 0;
        }

        for(int i=0;i<5;i++){
            bucket[hand.get(i).getValue()]++;
        }

        int max = 0;
        int secondmax = 1;
        for(int i=0;i<bucket.length;i++) {
            if (bucket[i] > max){
                max = bucket[i];
            }
            if (bucket[i]<= max && bucket[i]>0){
                secondmax = bucket[i];
            }
        }

        if (test.equals("isFourOfAKind")){
            if (max == 4) {
                return true;
            }
        }
        if (test.equals("isFullHouse")){
            if ((max == 3) && (secondmax == 2)) {
                return true;
            }
        }
        if (test.equals("isThreeOfAKind")){
            if(max == 3){
                return true;
            }
        }
        if (test.equals("isTwoPair")){
            if(max == 2 && secondmax == 2){
                return true;
            }
        }
        if (test.equals("isOnePair")){
            if(max == 2){
                return true;
            }
        }
        return false;
	}

    //COMPLETED
	public boolean isFlush(ArrayList<Card> hand) { 
	int suit = hand.get(0).getSuit(); 
	 	for (int i = 1; i < 5; i++){ 
			if (suit != (hand.get(i).getSuit())) { 
				return false; 
			}
		} 
		return true;
	} 

	public static void main(String[] args){
		Game gg = new Game();
		gg.play();

	}
		
}
