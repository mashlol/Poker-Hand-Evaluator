package start;

public class Main {

	public static void main(String[] args) {
		Card player1[] = new Card[5];
		Card player2[] = new Card[5];
		
		// MAKE PLAYER 1's HAND
		player1[0] = new Card(3, 0);
		player1[1] = new Card(3, 1);
		player1[2] = new Card(3, 2);
		player1[3] = new Card(3, 3);
		player1[4] = new Card(6, 1);
		
		
		// MAKE PLAYER 2's HAND
		player2[0] = new Card(3, 0);
		player2[1] = new Card(3, 1);
		player2[2] = new Card(3, 2);
		player2[3] = new Card(3, 3);
		player2[4] = new Card(7, 1);
		
		// PRINT P1's HAND
		System.out.println("Player 1's hand:");
		for (int i=0; i<5; i++)
			System.out.println(player1[i].toWords());
		
		System.out.println();
		
		// PRINT P2's HAND
		System.out.println("Player 2's hand:");
		for (int i=0; i<5; i++)
			System.out.println(player2[i].toWords());
		
		System.out.println();
		
		HandEvaluator he = new HandEvaluator(player1);
		System.out.println("Player 1 got a " + he.getPokerHandAsString() + " valued as " + he.getPokerHandAsValued());
		double p1Value = he.getPokerHandAsValued();
		he = new HandEvaluator(player2);
		System.out.println("Player 2 got a " + he.getPokerHandAsString() + " valued as " + he.getPokerHandAsValued());
		double p2Value = he.getPokerHandAsValued();
		
		if (p1Value > p2Value) {
			System.out.println("Player 1 wins");
		} else if (p2Value > p1Value) {
			System.out.println("Player 2 wins");
		} else {
			System.out.println("Tie");
		}
	}

}
