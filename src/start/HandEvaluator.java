package com.st.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEvaluator {

	private Card cards[];
	private String pattern;
	private double handValue;
	
	public HandEvaluator(Card cards[]) {
		this.cards = cards;
		
		evaluateHand();
	}
	
	public void evaluateHand() {
		byte tallyValues[] = new byte[14];	
		byte tallySuits[] = new byte[4];
		
		byte fours = 0;
		byte threes = 0;
		byte pair1 = 0;
		byte pair2 = 0;
		
		for (int i = 0; i < 5; i++) {
			tallyValues[cards[i].getRank()]++;	//same rank ,index is 1 to 13 
			tallySuits[cards[i].getSuit()]++;	//same suit
		}
		
		
		for (byte i = 13; i > 0; i--) {	//1 to 13 represent of Two to Ace, 13 numbers
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
		
		double tempRank = 0;
		
		if (sortedCardValues.get(0) + 4 == sortedCardValues.get(4)
				|| (sortedCardValues.get(4) == 13 && sortedCardValues.get(0) == 1 && sortedCardValues.get(0) + 3 == sortedCardValues.get(3))) {		//2345A
			pattern = "Straight";
			if(sortedCardValues.get(0) + 4 == sortedCardValues.get(4))
				tempRank = 56 + sortedCardValues.get(0);
			else
				tempRank = 56 + sortedCardValues.get(0) - 1;
			for (byte x=0; x<4; x++) {
				if (tallySuits[x] == 5) {
					tempRank += 56;
					pattern += " Flush";
				}
			}
		}
		
		if (fours > 0) {
			tempRank = 98 + fours + calcAddValue(sortedCardValues, 100, fours, 0);
			pattern = "Four of a Kind";
		} else if (threes > 0) {
			if (pair1 > 0) {
				tempRank = 84 + threes + (float) pair1 / 100 ;
				pattern = "Full House";
			} else {
				tempRank = 42 + threes  + calcAddValue(sortedCardValues, 100, threes, 0);
				pattern = "Three of a Kind";
			}
		} else if (pair1 > 0) {
			if (pair2 > 0) {
				tempRank = 28 + pair1 + (float) pair2 / 100 + calcAddValue(sortedCardValues, 10000, pair1, pair2);
				pattern = "Two Pair";
			} else {
				tempRank = 14 + pair1 + calcAddValue(sortedCardValues, 100,  pair1, 0);
				pattern = "Pair";
			}
		}
		
		if (tempRank == 0) {
			for (byte i=0; i<4; i++) {
				if (tallySuits[i] == 5) {
					tempRank = 70 + sortedCardValues.get(4) + (double) sortedCardValues.get(3) / 100 + (double) sortedCardValues.get(2) / 10000 + (double) sortedCardValues.get(1) / 1000000 + (double) sortedCardValues.get(0) / 100000000;
					pattern = "Flush";
				}
			}
		}
		
		if (tempRank == 0) {
			tempRank = sortedCardValues.get(4) + (double) sortedCardValues.get(3) / 100 + (double) sortedCardValues.get(2) / 10000 + (double) sortedCardValues.get(1) / 1000000 + (double) sortedCardValues.get(0) / 100000000;
			pattern = "High Card";
		}
		
		handValue = tempRank;
	}
	
	
	/**
	 * sortedCardValues  1 to 13
	 */
	public double calcAddValue(List<Byte> sortedCardValues,int rate, int exclude1, int exclude2){
		double tempRank = 0;
		for (int i = 4; i >= 0; i--) {
			if (sortedCardValues.get(i) == exclude1 || sortedCardValues.get(i) == exclude2)
				continue;
			tempRank += (float)  sortedCardValues.get(i) /rate;
			rate *= 100;
		}
		return tempRank;
	}
	
	public String getPokerHandAsString() {
		return pattern;
	}
	
	public double getPokerHandAsValued() {
		return handValue;
	}
	
	
}
