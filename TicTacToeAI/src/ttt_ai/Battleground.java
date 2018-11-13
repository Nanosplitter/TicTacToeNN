package ttt_ai;

import java.util.ArrayList;

//MAKE HUMAN-TO-NN TESTER, GET BEST NN AFTER TRAINING

public class Battleground {
	Generation generation = new Generation(100);
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
			bestNNs.add(generation.getSecondBest());
			bestNNs.add(generation.getThirdBest());
			bestNNs.add(generation.getFourthBest());
			if (i < genNumber - 1) {
				//System.out.println("Changed");
				generation.nextGen(bestNNs);
			}
		}
		HumanNN nn = new HumanNN(27,9,3,5);
		NNFiler nnf = new NNFiler();
		nnf.write(generation.getBest(), 2);
		while(true) {
			Game game = new Game(nn, nnf.read(2));
			game.playGame();
			Game game2 = new Game(nnf.read(2), nn);
			game2.playGame();
		}
	}
	
	
	
	public void runBattles(int genNum) {
		currentGen = generation.getGen(false);
		for (NN n1 : currentGen) {
			for (NN n2 : currentGen) {
				if(!n1.equals(n2)) {
					Game game = new Game(n1, n2);
					game.playGame();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new Battleground(1);
	}
}
