package start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEvaluator {

	private Card cards[];
	private String pattern;
	private float handValue;
	
	public HandEvaluator(Card cards[]) {
		this.cards = cards;
		
		evaluateHand();
	}
	
	public void evaluateHand() {
		byte tallyValues[] = new byte[13];
		byte tallySuits[] = new byte[4];
		
		byte fours = 0;
		byte threes = 0;
		byte pair1 = 0;
		byte pair2 = 0;
		
		for (int i=0; i<5; i++) {
			tallyValues[cards[i].getRank()]++;
			tallySuits[cards[i].getSuit()]++;
		}
		
		
		for (byte i=12; i>=0; i--) {
			if (tallyValues[i] == 4) {
				fours = i;
			} else if (tallyValues[i] == 3) {
				threes = i;
			} else if (tallyValues[i] == 2) {
				if (pair1 == 0)
					pair1 = i;
				else
					pair2 = i;
			}
		}
		
		List<Byte> sortedCardValues = new ArrayList<Byte>();
		
		for (byte i=0; i<5; i++) {
			sortedCardValues.add(cards[i].getRank());
		}
		Collections.sort(sortedCardValues);
		
		float tempRank = 0;
		
		if (sortedCardValues.get(0) + 4 == sortedCardValues.get(4)) {
			pattern = "Straight";
			tempRank = 56 + sortedCardValues.get(4);
			for (byte x=0; x<4; x++) {
				if (tallySuits[x] == 5) {
					tempRank += 56;
					pattern += " Flush";
				}
			}
		}
		
		if (fours > 0) {
			tempRank = 98 + fours;
			pattern = "Four of a Kind";
		} else if (threes > 0) {
			if (pair1 > 0) {
				tempRank = 84 + threes;
				pattern = "Full House";
			} else {
				tempRank = 42 + threes;
				pattern = "Three of a Kind";
			}
		} else if (pair1 > 0) {
			if (pair2 > 0) {
				tempRank = 28 + pair1 + (float) pair2 / 100 + (float) sortedCardValues.get(4) / 10000;
				pattern = "Two Pair";
			} else {
				tempRank = 14 + pair1 + (float) sortedCardValues.get(4) / 100 + (float) sortedCardValues.get(3) / 10000 + (float) sortedCardValues.get(2) / 1000000;
				pattern = "Pair";
			}
		}
		
		if (tempRank == 0) {
			for (byte i=0; i<4; i++) {
				if (tallySuits[i] == 5) {
					tempRank = 70 + sortedCardValues.get(4) + (float) sortedCardValues.get(3) / 100 + (float) sortedCardValues.get(2) / 10000 + (float) sortedCardValues.get(1) / 1000000 + (float) sortedCardValues.get(0) / 100000000;
					pattern = "Flush";
				}
			}
		}
		
		if (tempRank == 0) {
			tempRank = sortedCardValues.get(4) + (float) sortedCardValues.get(3) / 100 + (float) sortedCardValues.get(2) / 10000 + (float) sortedCardValues.get(1) / 1000000 + (float) sortedCardValues.get(0) / 100000000;
			pattern = "High Card";
		}
		
		handValue = tempRank;
	}
	
	public String getPokerHandAsString() {
		return pattern;
	}
	
	public double getPokerHandAsValued() {
		return handValue;
	}
	
	
}
