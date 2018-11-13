package ttt_ai;
import java.util.ArrayList;

public class BoardState {
	ArrayList<Character> board;
	public BoardState(ArrayList<Character> charBoard) {
		board = charBoard;
	}
	
	public String checkForWin() {
		//ADD TIE CHECK
		char[] all = {board.get(0), board.get(1), board.get(2), board.get(3), board.get(4), board.get(5), board.get(6), board.get(7), board.get(8)};
		if (count(all, ' ') == 0) {
			return "TIE";
		}
		//Top Row
		char[] top = {board.get(0), board.get(1), board.get(2)};
		if (count(top, 'X') == 3) {
			return "X";
		} else if (count(top, 'O') == 3) {
			return "O";
		}

		//horz Row
		char[] horz = {board.get(3), board.get(4), board.get(5)};
		if (count(horz, 'X') == 3) {
			return "X";
		} else if (count(horz, 'O') == 3) {
			return "O";
		}
		
		//Bottom Row
		char[] bottom = {board.get(6), board.get(7), board.get(8)};
		if (count(bottom, 'X') == 3) {
			return "X";
		} else if (count(bottom, 'O') == 3) {
			return "O";
		}
		
		//Left Row
		char[] left = {board.get(0), board.get(3), board.get(6)};
		if (count(left, 'X') == 3) {
			return "X";
		} else if (count(left, 'O') == 3) {
			return "O";
		}
		
		//Vert Row
		char[] vert = {board.get(1), board.get(4), board.get(7)};
		if (count(vert, 'X') == 3) {
			return "X";
		} else if (count(vert, 'O') == 3) {
			return "O";
		}
		
		//Right Row
		char[] right = {board.get(2), board.get(5), board.get(8)};
		if (count(right, 'X') == 3) {
			return "X";
		} else if (count(right, 'O') == 3) {
			return "O";
		}
		
		//Left Diag
		char[] leftDiag = {board.get(0), board.get(4), board.get(8)};
		if (count(leftDiag, 'X') == 3) {
			return "X";
		} else if (count(leftDiag, 'O') == 3) {
			return "O";
		}
		
		//Right Diag Row
		char[] rightDiag = {board.get(2), board.get(4), board.get(6)};
		if (count(rightDiag, 'X') == 3) {
			return "X";
		} else if (count(rightDiag, 'O') == 3) {
			return "O";
		}
		
		return "NOT COMPLETE";
		
		
	}
	
	public int checkDanger(char player) {
		int xDanger = 0;
		int oDanger = 0;
		
		//Top Row
		char[] top = {board.get(0), board.get(1), board.get(2)};
		if (count(top, 'X') == 2 && count(top, 'O') == 0) {
			xDanger++;
		} else if (count(top, 'O') == 2 && count(top, 'X') == 0) {
			oDanger++;
		}

		//horz Row
		char[] horz = {board.get(3), board.get(4), board.get(5)};
		if (count(horz, 'X') == 2 && count(horz, 'O') == 0) {
			xDanger++;
		} else if (count(horz, 'O') == 2 && count(horz, 'X') == 0) {
			oDanger++;
		}
		
		//Bottom Row
		char[] bottom = {board.get(6), board.get(7), board.get(8)};
		if (count(bottom, 'X') == 2 && count(bottom, 'O') == 0) {
			xDanger++;
		} else if (count(bottom, 'O') == 2 && count(bottom, 'X') == 0) {
			oDanger++;
		}
		
		//Left Row
		char[] left = {board.get(0), board.get(3), board.get(6)};
		if (count(left, 'X') == 2 && count(left, 'O') == 0) {
			xDanger++;
		} else if (count(left, 'O') == 2 && count(left, 'X') == 0) {
			oDanger++;
		}
		
		//Vert Row
		char[] vert = {board.get(1), board.get(4), board.get(7)};
		if (count(vert, 'X') == 2 && count(vert, 'O') == 0) {
			xDanger++;
		} else if (count(vert, 'O') == 2 && count(vert, 'X') == 0) {
			oDanger++;
		}
		
		//Right Row
		char[] right = {board.get(2), board.get(5), board.get(8)};
		if (count(right, 'X') == 2 && count(right, 'O') == 0) {
			xDanger++;
		} else if (count(right, 'O') == 2 && count(right, 'X') == 0) {
			oDanger++;
		}
		
		//Left Diag
		char[] leftDiag = {board.get(0), board.get(4), board.get(8)};
		if (count(leftDiag, 'X') == 2 && count(leftDiag, 'O') == 0) {
			xDanger++;
		} else if (count(leftDiag, 'O') == 2 && count(leftDiag, 'X') == 0) {
			oDanger++;
		}
		
		//Right Diag Row
		char[] rightDiag = {board.get(2), board.get(4), board.get(6)};
		if (count(rightDiag, 'X') == 2 && count(rightDiag, 'O') == 0) {
			xDanger++;
		} else if (count(rightDiag, 'O') == 2 && count(rightDiag, 'X') == 0) {
			oDanger++;
		}
		
		if (player == 'X') {
			return xDanger;
		} else {
			return oDanger;
		}
	}
	
	public int count(char[] list, char elem) {
		int c = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] == elem) {
				c++;
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		ArrayList<Character> b = new ArrayList<Character>();
		b.add('O');
		b.add('O');
		b.add(' ');
		b.add(' ');
		b.add('X');
		b.add(' ');
		b.add(' ');
		b.add('X');
		b.add(' ');
		
		BoardState bs = new BoardState(b);
		System.out.println(bs.checkDanger(' '));
	}
	
}
