package ttt_ai;

public class Move implements Comparable<Move> {
	public int index;
	public int defScore;
	public int attScore;
	@Override
	public int compareTo(Move m) {
		if (defScore > m.defScore) {
			return -1;
		} else if (defScore == m.defScore) {
			if (attScore > m.attScore) {
				return -1;
			} else if (attScore < m.attScore) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 1;
		}
	}
}
