package ttt_ai;

import java.util.*;

public class SmartNN extends NN {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2419991345371814571L;
	int randomFactor;
	Random rnd = new Random();
	public SmartNN(int numInputs, int numOutputs, int numHiddenLayers, int widthHiddenLayers) {
		super(numInputs, numOutputs, numHiddenLayers, widthHiddenLayers);
		randomFactor = 0;
	}
	
	public ArrayList<Character> rotateBoard(ArrayList<Character> board) {
		ArrayList<Character> temp = new ArrayList<Character>();
		switch(randomFactor) {
			case 0:
				temp = board;
			case 1:
				temp.add(board.get(2));
				temp.add(board.get(5));
				temp.add(board.get(8));
				temp.add(board.get(1));
				temp.add(board.get(4));
				temp.add(board.get(7));
				temp.add(board.get(0));
				temp.add(board.get(3));
				temp.add(board.get(6));
				break;
			case 2:
				temp.add(board.get(8));
				temp.add(board.get(7));
				temp.add(board.get(6));
				temp.add(board.get(5));
				temp.add(board.get(4));
				temp.add(board.get(3));
				temp.add(board.get(2));
				temp.add(board.get(1));
				temp.add(board.get(0));
				break;
			case 3:
				temp.add(board.get(6));
				temp.add(board.get(3));
				temp.add(board.get(0));
				temp.add(board.get(7));
				temp.add(board.get(4));
				temp.add(board.get(1));
				temp.add(board.get(8));
				temp.add(board.get(5));
				temp.add(board.get(2));
				break;
		}
		return temp;
	}
	
	public int makeMove(float[] board, int playerOrder) {
		int[] state = new int[board.length];
		for (int i = 0; i < board.length; i++) {
			if (board[i] == -1) {
				state[i] = 2;
			} else {
				state[i] = (int)board[i];
			}
		}
//		for(Integer i : state) {
//			System.out.print(i + ", ");
//		}
//		System.out.println();
		return negaMax(1, state)[1];
		
//		ArrayList<Character> charArray = new ArrayList<Character>();
//		for (Float f : board) {
//			if (f == 1) {
//				charArray.add('X');
//			} else if (f == -1) {
//				charArray.add('O');
//			} else {
//				charArray.add(' ');
//			}
//		}
//		//charArray = rotateBoard(charArray);
//		//System.out.println(charArray);
//		
//		Move move;
//		ArrayList<Move> moves = new ArrayList<Move>();
//		for (int i = 0; i < charArray.size(); i++) {
//			if (charArray.get(i) == ' ') {
//				move = checkMove(charArray, i);
//				moves.add(move);
//			}
//		}
//		Collections.sort(moves);
//		//System.out.println(moves.get(0).index);
//		return moves.get(0).index;
	}
	
	private int[] negaMax(int p, int[] state)
	{
		int move = -1;
		// valid moves from this board state
		List<Integer> validMoves = new ArrayList<Integer>();
		for(int i = 0; i < state.length; i++) {
			if(state[i] == 0)
				validMoves.add(i);
		}
		Collections.shuffle(validMoves);
		// game over
		int reward = eval(state);
		if(reward != 0 || validMoves.size() < 1)
			return (new int[] { reward, move });
		// game not over: check each sub-board
		int bestValue = 0;
		// computer
		if(p == 1) {
			bestValue = Integer.MIN_VALUE;
			for(int i = 0; i < validMoves.size(); i++) {
				int[] tmpState = new int[9];
				System.arraycopy(state, 0, tmpState, 0, state.length);
				tmpState[validMoves.get(i)] = p;

				int val = negaMax(2, tmpState)[0];
				if(val > bestValue) {
					bestValue = val;
					move = validMoves.get(i);
				}
			}
		}
		// human
		else
		{
			bestValue = Integer.MAX_VALUE;
			for(int i = 0; i < validMoves.size(); i++) {
				int[] tmpState = new int[9];
				System.arraycopy(state, 0, tmpState, 0, state.length);
				tmpState[validMoves.get(i)] = p;
				int val = negaMax(1, tmpState)[0];
				if(val < bestValue) {
					bestValue = val;
					move = validMoves.get(i);
				}
			}
		}
		return (new int[] { bestValue, move });
	}
	
	private int eval(int[] state)
	{
		if(isWinner(state, 1))
			return 1;
		else if(isWinner(state, 2))
			return -1;
		else
			return 0;
	}
	
	public Move checkMove(ArrayList<Character> board, int moveIndex) {
		board.set(moveIndex, 'X');
		Move m = new Move();
		BoardState bs = new BoardState(board);
		int MyDanger = bs.checkDanger('X');
		int EnemyDanger = bs.checkDanger('O');
		m.defScore = 8 - EnemyDanger;
		m.attScore = MyDanger;
		m.index = moveIndex;
		if (bs.checkForWin() == "X") {
			m.defScore = Integer.MAX_VALUE;
		}
		board.set(moveIndex, ' ');
		return m;	
	}
	
	private boolean isWinner(int[] state, int player)
	{
		if((state[0]==state[1] && state[1]==state[2] && state[0]==player) // horizontal
				|| (state[3]==state[4] && state[4]==state[5] && state[3]==player) 
				|| (state[6]==state[7] && state[7]==state[8] && state[6]==player) 
				|| (state[0]==state[3] && state[3]==state[6] && state[0]==player) // vertical
				|| (state[1]==state[4] && state[4]==state[7] && state[1]==player)
				|| (state[2]==state[5] && state[5]==state[8] && state[2]==player)
				|| (state[0]==state[4] && state[4]==state[8] && state[0]==player) // diagonal
				|| (state[2]==state[4] && state[4]==state[6] && state[2]==player))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		HumanNN nn = new HumanNN(18, 9, 3, 5);
		NNFiler nnf = new NNFiler();
		nnf.write(new SmartNN(18, 9, 3, 5), -1);
		while(true) {
			Game game = new Game(nn, new SmartNN(18, 9, 3, 5));
			game.playGame();
			Game game2 = new Game(new SmartNN(18, 9, 3, 5), nn);
			game2.playGame();
		}
	}

}
