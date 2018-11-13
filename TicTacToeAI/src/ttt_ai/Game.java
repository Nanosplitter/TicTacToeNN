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
			//O's turn
			if (turn % 2 == 0) {
				// System.out.println("O turn");
				BoardState bsBefore = new BoardState(makeBoardStateArray('O', boardO));
				int myDangerLevel = bsBefore.checkDanger('O');
				int enemyDangerLevel = bsBefore.checkDanger('X');
				int oMove = netO.makeMove(boardO, 1);
				//System.out.println("2nd's move: " + oMove);
				boardO[oMove] = 1;
				boardX[oMove] = -1;
				BoardState bs = new BoardState(makeBoardStateArray('O', boardO));
				//If I am more dangerous
				if(myDangerLevel > 0) {
					if(bs.checkDanger('O') == myDangerLevel) {
						oPoints-=1;
					} else {
						oPoints+=2;
					}
				}
				//If enemy is less dangerous
				//System.out.println("First DangerLevel: " + bs.checkDanger('O'));
				if(enemyDangerLevel > 0) {
					if(bs.checkDanger('X') == enemyDangerLevel) {
						oPoints-=5;
					} else {
						oPoints+=2;
					}
				}
				if (bs.checkForWin() == "O") {
					netO.reportResult(turn, 5 + oPoints + (9 - turn), false);
					netX.reportResult(turn, -5 + xPoints + turn - 3, true);
					winner = true;
					break GAMELOOP;
				}
			} else {
				//X's turn
				// System.out.println("X turn");
				BoardState bsBefore = new BoardState(makeBoardStateArray('X', boardX));
				int dangerLevel = bsBefore.checkDanger('X');
				int enemyDangerLevel = bsBefore.checkDanger('O');
				int xMove = netX.makeMove(boardX, 0);
				//System.out.println("1st's move: " + xMove);
				boardX[xMove] = 1;
				boardO[xMove] = -1;
				BoardState bs = new BoardState(makeBoardStateArray('X', boardX));
				//If I am more dangerous
				if(dangerLevel > 0) {
					if(bs.checkDanger('X') == dangerLevel) {
						xPoints-=1;
					} else {
						xPoints+=2;
					}
				}
				//If enemy is less dangerous
				//System.out.println("Second DangerLevel: " + bs.checkDanger('X'));
				if(enemyDangerLevel > 0) {
					if(bs.checkDanger('O') == enemyDangerLevel) {
						xPoints-=5;
					} else {
						xPoints+=2;
					}
				}
				if (bs.checkForWin() == "X") {
					netX.reportResult(turn, 7 + xPoints + (9 - turn), false);
					netO.reportResult(turn, -7 + oPoints + turn - 3, true);
					winner = true;
					break GAMELOOP;
				}
			}
		}
		if (!winner) {
			netX.reportResult(9, 6 + xPoints + 4, false);
			netO.reportResult(9, 6 + oPoints + 4, false);
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
