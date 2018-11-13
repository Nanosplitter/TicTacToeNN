package ttt_ai;

import java.util.Scanner;

public class PlaySavedNN {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("ID of NN file: ");
		int id = scan.nextInt();
		
		System.out.println("Getting NN with id '" + id + "'...");
		
		HumanNN humNN = new HumanNN(19, 4, 4, 9);
		NNFiler nnf = new NNFiler();
		while(true) {
			Game game = new Game(humNN, nnf.read(id));
			game.playGame();
			Game game2 = new Game(nnf.read(id), humNN);
			game2.playGame();
		}
	}

}
