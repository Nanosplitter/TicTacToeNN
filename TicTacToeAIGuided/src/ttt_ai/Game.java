package ttt_ai;

import java.util.ArrayList;

public class Game {
	NN netX;
	NN netO;

	int turns;
	char winner;

	float[] boardX = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	float[] boardO = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public Game(NN nx, NN no) {
		netX = nx;
		netO = no;
	}

	public void playGame() {
		boolean winner = false;
		int oPoints = 0;
		int xPoints = 0;
		GAMELOOP: for (int turn = 1; turn < 10; turn++) {
			if (turn % 2 == 0) {
				// System.out.println("O turn");
				int oMove = netO.makeMove(boardO);
//				int correctMove = netX.makeMove(boardO);
//				if(oMove == correctMove) {
//					oPoints += 5;
//				}
				boardO[oMove] = 1;
				boardX[oMove] = -1;
				BoardState bs = new BoardState(makeBoardStateArray('O', boardO));
				//If I am more dangerous
//				if(bs.checkDanger('O') > myDangerLevel) {
//					oPoints+=2;
//				}
				//If enemy is less dangerous
				//System.out.println("First DangerLevel: " + bs.checkDanger('O'));
//				if(bs.checkDanger('X') < enemyDangerLevel) {
//					//System.out.println("O Save");
//					oPoints+=3;
//				}
				if (bs.checkForWin() == "O") {
					netO.reportResult(turn, 0 + oPoints);
					netX.reportResult(turn, -50 + xPoints);
					winner = true;
					break GAMELOOP;
				}
			} else {
				// System.out.println("X turn");
				int xMove = netX.makeMove(boardX);
//				int correctMove = netO.makeMove(boardX);
//				if (xMove == correctMove) {
//					xPoints += 5;
//				}
				boardX[xMove] = 1;
				boardO[xMove] = -1;
				BoardState bs = new BoardState(makeBoardStateArray('X', boardX));
//				//If I am more dangerous
//				if(bs.checkDanger('X') > dangerLevel) {
//					xPoints+=3;
//				}
				//If enemy is less dangerous
				//System.out.println("Second DangerLevel: " + bs.checkDanger('X'));
//				if(bs.checkDanger('O') < enemyDangerLevel) {
//					//System.out.println("X Save");
//					xPoints+=2;
//				}
				if (bs.checkForWin() == "X") {
					netX.reportResult(turn, 0 + xPoints);
					netO.reportResult(turn, -50 + oPoints);
					winner = true;
					break GAMELOOP;
				}
			}
		}
		if (!winner) {
			netX.reportResult(9, 50 + xPoints);
			netO.reportResult(9, 50 + oPoints);
		}
	}

	public ArrayList<Character> makeBoardStateArray(char from, float[] board) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		
		if (from == 'X') {
			for (Float f : board) {
				if (f == 1) {
					charArray.add('X');
				} else if (f == -1) {
					charArray.add('O');
				} else {
					charArray.add(' ');
				}
			}
		} else {
			for (Float f : board) {
				if (f == 1) {
					charArray.add('O');
				} else if (f == -1) {
					charArray.add('X');
				} else {
					charArray.add(' ');
				}
			}
		}
		
		return charArray;
	}
	public NN getNetX() {
		return netX;
	}

	public NN getNetO() {
		return netO;
	}

}
