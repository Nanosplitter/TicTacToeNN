package ttt_ai;

import java.util.ArrayList;


public class Battleground {
	Generation generation = new Generation(50);
	ArrayList<NN> currentGen;
	ArrayList<NN> bestNNs = new ArrayList<NN>();
	public Battleground(int genNumber) {
		for (int i = 0; i < genNumber; i++) {
			runBattles(i);
			System.out.println("Gen: " + (i + 1) + " Points: " + generation.getBest().winCount);
			//System.out.println(generation.getBest().turnCounts.size());
			
//			for (NN nn : generation.getGen(true)) {
//				System.out.print(nn.winCount + ", ");
//			}
//			System.out.println();
			//System.out.println("Test -- Wins: " + generation.testBest(generation.getBest()).winCount);
			bestNNs.clear();
			bestNNs.add(generation.getBest());
			if (i < genNumber - 1) {
				//System.out.println("Changed");
				generation.nextGen(bestNNs);
			}
		}
		HumanNN nn = new HumanNN(27,9,3,5);
		while(true) {
			Game game = new Game(nn, generation.getBest());
			game.playGame();
			Game game2 = new Game(generation.getBest(), nn);
			game2.playGame();
		}
	}
	
	
	
	public void runBattles(int genNum) {
		currentGen = generation.getGen(false);
		SmartNN smart = new SmartNN(18, 9, 3, 3);
		for (NN nn : currentGen) {
			for (int i = 0; i < 2; i++) {
				Game game = new Game(nn, smart);
				game.playGame();
				Game game2 = new Game(smart, nn);
				game2.playGame();
			}
		}
	}
	
	public static void main(String[] args) {
		new Battleground(50);
	}
}
