package ttt_ai;

public class Game3 {
	NN netX;
	NN netO;

	int turns;
	char winner;

	float[] boardX = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	float[] boardO = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public Game3(NN nx, NN no) {
		netX = nx;
		netO = no;
	}

	public void playGame() {
		boolean winner = false;
		GAMELOOP: for (int turn = 1; turn < 10; turn++) {

			if (turn % 2 == 0) {
				// System.out.println("O turn");
				int oMove = netO.makeMove(boardO);
				boardO[oMove] = 1;
				boardX[oMove] = -1;

				if ((boardO[0] == 1 && boardO[4] == 1 && boardO[8] == 1)
						|| (boardO[0] == 1 && boardO[1] == 1 && boardO[2] == 1)
						|| (boardO[0] == 1 && boardO[3] == 1 && boardO[6] == 1)
						|| (boardO[1] == 1 && boardO[4] == 1 && boardO[7] == 1)
						|| (boardO[2] == 1 && boardO[4] == 1 && boardO[6] == 1)
						|| (boardO[2] == 1 && boardO[5] == 1 && boardO[8] == 1)
						|| (boardO[3] == 1 && boardO[4] == 1 && boardO[5] == 1)
						|| (boardO[2] == 1 && boardO[4] == 1 && boardO[6] == 1)
						|| (boardO[6] == 1 && boardO[7] == 1 && boardO[8] == 1)) {
					netO.reportResult(turn, 1);
					netX.reportResult(turn, -1);
					winner = true;
					break GAMELOOP;
				}
			} else {
				// System.out.println("X turn");
				int xMove = netX.makeMove(boardX);
				boardX[xMove] = 1;
				boardO[xMove] = -1;

				if ((boardX[0] == 1 && boardX[4] == 1 && boardX[8] == 1)
						|| (boardX[0] == 1 && boardX[1] == 1 && boardX[2] == 1)
						|| (boardX[0] == 1 && boardX[3] == 1 && boardX[6] == 1)
						|| (boardX[1] == 1 && boardX[4] == 1 && boardX[7] == 1)
						|| (boardX[2] == 1 && boardX[4] == 1 && boardX[6] == 1)
						|| (boardX[2] == 1 && boardX[5] == 1 && boardX[8] == 1)
						|| (boardX[3] == 1 && boardX[4] == 1 && boardX[5] == 1)
						|| (boardX[2] == 1 && boardX[4] == 1 && boardX[6] == 1)
						|| (boardX[6] == 1 && boardX[7] == 1 && boardX[8] == 1)) {
					netX.reportResult(turn, 1);
					netO.reportResult(turn, -1);
					winner = true;
					break GAMELOOP;
				}
			}
		}
		if (!winner) {
			netX.reportResult(9, 1);
			netO.reportResult(9, 1);
		}
	}

	public NN getNetX() {
		return netX;
	}

	public NN getNetO() {
		return netO;
	}

}
