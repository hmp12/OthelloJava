import java.util.Arrays;

public class AI {
	
	Reversi r;
	int max_player, level, corner, edge, early;
	int mobi;
	
	public void init(Reversi re, int table[][], int l, int c, int e, int m, int ea, int player) {	
		r = re;
		level = l;
		corner = c;
		edge = e; 
		mobi = m;
		early = ea;
		max_player = player;
		play(table, level, player, -1000, 1000);
	}
	
	public int value(int i, int j, int player, int table[][]) {	
		int value = r.checkMove(i, j, player, table);
		if (value == 0) return -10000;
		
		int mobility;
		if (player == 1) mobility = r.availMove(2, false);
		else  mobility = r.availMove(1, false);
		value-=mobility*mobi;
		
		int bonus = 0;
		if (r.total() < early) {
			if (isCorner(i, j)) bonus+=corner;
			else if (isRenroc(i, j)) bonus-=corner/2;
			else if (isEdge(i, j)) bonus+=edge;
			else if (isEgde(i, j)) bonus-=edge/2;
		}			
		
		value += bonus - mobility*mobi;
		return value;
	}
	
	public int play(int table[][], int lv, int player, int alpha, int beta) {
		int x, y, i, j, temp_value, value;
		int temp_table[][];
		int max = -1000, min = 1000;
		boolean noMove = true;
		x = y = 0;
		temp_value = 0;
		for (i=0;i<8;i++)
			for (j=0;j<8;j++) {
				temp_table = Clone.array2d(table);
				temp_value = value(i, j, player, temp_table);
				if (temp_value != -10000) {
					if (lv > 1)
						if (player != 1) value = play(temp_table, lv - 1, 1, alpha, beta);
						else value = play(temp_table, lv - 1, 2, alpha, beta);
					else value = 0;
					
					if (player == max_player) {
						value += temp_value;
						if (value > max) {
							if (lv == level) {
								x = i;
								y = j;
							}
							max = value;
						}
						if (value >= beta) return value;
						if (value > alpha) alpha = value;
					}
					else {
						value -= temp_value;
						if (value < min) min = value;
						if (value <= alpha) return value;
						if (value < beta) alpha = value;
					}
					
					noMove = false;
				}		
			}
		if (noMove) 
			if (lv > 1)
				if (player != 1) return play(table, lv - 1, 1, alpha, beta);
				else return play(table, lv - 1, 2, alpha, beta);
			else return 0;
		if (lv == level) {
			r.choice(x, y, true);	
		}
		if (player == max_player) return max;
		else return min;
	}
	
	public boolean isCorner(int x, int y) {
		if (x == 0 || x == 7) {
			if (y == 0 || y == 7) return true;
		}
		return false;
	}
	
	public boolean isRenroc(int x, int y) {
		if (x == 0 || x == 7) {
			if (y == 1 || y == 6) return true;
		}
		if (x == 1 || x == 6) {
			if ( y == 0 || y == 1 || y == 6 || y == 7) return true;
		}
		return false;
	}
	
	public boolean isEdge(int x, int y) {
		if (x == 0 || x == 7 || y == 0 || y == 7) return true;
		return false;
	}
	
	public boolean isEgde(int x, int y) {
		if (x == 1 || x == 6 || y == 1 || y == 6) return true;
		return false;
	}
}
