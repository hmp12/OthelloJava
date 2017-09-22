
public class Clone {
	public static int[][]array2d(int a[][]) {
		int i, b[][];
		b = a.clone();
		for (i=0;i<b.length;i++)
			b[i] = b[i].clone();
		return b;
	}
}
