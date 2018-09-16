package ttt_ai;

import java.util.Scanner;

public class HumanNN extends NN {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Scanner scan = new Scanner(System.in);
	public HumanNN(int numInputs, int numOutputs, int numHiddenLayers, int widthHiddenLayers) {
		super(numInputs, numOutputs, numHiddenLayers, widthHiddenLayers);
	}
	
	public synchronized int makeMove(float[] board) {
		updateBoard(board);
		return scan.nextInt();
	}
	
	
	public void updateBoard(float[] board) {
		char[] boardChars = new char[9];
		for (int i = 0; i < 9; i++) {
			switch ((int)board[i]) {
				case 1:
					boardChars[i] = 'O';
					break;
				case -1:
					boardChars[i] = 'X';
					break;
				default:
					boardChars[i] = ' ';
			}
		}
		System.out.println("0---1---2");
		System.out.println(boardChars[0] + " | " + boardChars[1] + " | " + boardChars[2]);
		System.out.println("3---4---5");
		System.out.println(boardChars[3] + " | " + boardChars[4] + " | " + boardChars[5]);
		System.out.println("6---7---8");
		System.out.println(boardChars[6] + " | " + boardChars[7] + " | " + boardChars[8]);
		System.out.println();
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println();
	}

}
