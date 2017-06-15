package start;

public class Card {
	

    /**
     * from 1 to 13, represent Two to Ace
     */
    private byte rank;
    private byte suit;

    
    public Card(int i, int j) {
        this.rank = (byte) (i % 13);
        this.suit = (byte) (j % 4);
        
        if(rank == 0)
        	rank = 13;
    }
    
    public byte getRank() {
    	return rank;
    }
    
    public byte getSuit() {
    	return suit;
    }
    
    public String valueToString () {
		String value = "Error";
		
		int temp = this.rank % 13;
		
		if (temp == 0) {
			value = "Ace";
		} else if (temp == 1) {
			value = "Two";
		} else if (temp == 2) {
			value = "Three";
		} else if (temp == 3) {
			value = "Four";
		} else if (temp == 4) {
			value = "Five";
		} else if (temp == 5) {
			value = "Six";
		} else if (temp == 6) {
			value = "Seven";
		} else if (temp == 7) {
			value = "Eight";
		} else if (temp == 8) {
			value = "Nine";
		} else if (temp == 9) {
			value = "Ten";
		} else if (temp == 10) {
			value = "Jack";
		} else if (temp == 11) {
			value = "Queen";
		} else if (temp == 12) {
			value = "King";
		}
		
		return value;
	}
	
	public boolean isFaceCard() {
		return this.rank == 10 || this.rank == 11 || this.rank == 12;
	}
	
	public String suitToString () {
		String suit = "Errors";
		
		if (this.suit == 0) {
			suit = "Spades";
		} else if (this.suit == 1) {
			suit = "Clubs";
		} else if (this.suit == 2) {
			suit = "Hearts";
		} else if (this.suit == 3) {
			suit = "Diamonds";
		}
		
		
		return suit;
	}
	
	public String toWords () {
		String words = "";
		
		words = valueToString() + " of " + suitToString();
		
		return words;
	}
    
}
