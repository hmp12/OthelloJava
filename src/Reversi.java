
public class Reversi {
	
	
	final int N = 8;
	private int table[][] = new int[N][N];
	private int available[][] = new int[N][N];
	int X[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	int Y[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	private int mode, player;
	
	public int getMode() {
        return mode;
    }

    public int getPlayer() {
        return player;
    }

    public int[][] getTable() {
        return table;
    }

    public int[][] getAvailable() {
        return available;
    }
    
 
	
	public void init(int m) {
		int i,j;
		for (i=0;i<N;i++)
			for (j=0;j<N;j++)
				table[i][j] = 0;
	
		table[N/2-1][N/2] = 1;
		table[N/2][N/2-1] = 1;
		table[N/2-1][N/2-1] = 2;
		table[N/2][N/2] = 2;
		
		mode = m;
		player = 1;
		availMove(player, true);
	}
	
	public int choice(int x, int y, boolean ai) {	
		if (player == 2 && mode > 0 && !ai) return 0;
		if (table[x][y] != 0) return 0;
		int m = checkMove(x, y, player, table);
		return m;
	}
	
	public int checkMove(int x, int y, int player, int table[][]) {
		int i, z;
		int count = 0;
		if (table[x][y] != 0) return 0;
		for (i = 0; i < 8; i++) {
			z = check(x + X[i], y + Y[i], i, player, table);
			if (z > 0) count += z;	
		}
		if (count > 0)
			table[x][y] = player;
		
		
		return count;
	}
	
	public int check(int x, int y, int i, int player, int table[][]) {
		int z;
		if (x<0 || x>N-1 || y<0 || y>N-1 || table[x][y] == 0) return -1;
		else if (table[x][y] == player) return 0;
		else {
			z = check(x + X[i], y+ Y[i], i, player, table);
			if (z>=0) {
				table[x][y] = player;
				return ++z;
			}
			else return -1;
		}
	}

	
	public void changePlayer() {
		if (player == 1) {
			player = 2;
		}
		else {
			player = 1;
		}
		
		if (availMove(player, true) == 0) changePlayer();
	}
	
	public int availMove(int player, boolean choice) {
		int temp_table[][];
        int m, i, j;
        int count = 0;
        if (choice)
        	for (i=0;i<8;i++)
        		for (j=0;j<8;j++)
        			available[i][j] = 0;
        for (i=0;i<8;i++)
            for (j=0;j<8;j++) {
                temp_table = Clone.array2d(table);
                m = checkMove(i, j, player, temp_table);
                if (m > 0){
                    count++;
                    if (choice) available[i][j] = 1;
                }
            }
        return count;       
	}
	
	public int total() {
		int i, j, total = 0;
		for (i=0;i<8;i++)
    		for (j=0;j<8;j++) {
    			if (table[i][j] !=0) total++;
    		}
		return total;
	}
	
	public boolean isFinish() {
		if (availMove(1, false)==0 && availMove(2, false)==0) return true;
		return false;
	}
	
}
