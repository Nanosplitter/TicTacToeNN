package ttt_ai;

import java.util.Random;

public class standardPlayer extends NN {

	/**
	 * 
	 */
	Random rnd = new Random();
	private static final long serialVersionUID = 8317669610276028611L;

	public standardPlayer(int numInputs, int numOutputs, int numHiddenLayers, int widthHiddenLayers) {
		super(numInputs, numOutputs, numHiddenLayers, widthHiddenLayers);
	}
	
	public int makeMove(float[] board) {
//		int[] prefMoves = {4, 0, 2, 6, 8, 3, 1, 5, 7};
////		for (Float f : board) {
////			System.out.print(f + ", ");
////		}
////		System.out.println();
//		for (Integer i : prefMoves) {
//			if (board[i] == 0) {
////				System.out.println("RETURNING i: " + i);
//				return i;
//				
//			}
//		}
//		System.out.println("FAIL");
//		return 0;
		
		while (true) {
			int temp = rnd.nextInt(9);
			if (board[temp] == 0) {
				return temp;
			}
		}
		
		
	}

}
