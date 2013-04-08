public class BST {

	private double[][] expect;
	private double[][] weight;
	private int[][] root;

	public void optimalBST(double[] p, double[] q, int n) {
		expect = new double[n + 3][n + 2];
		weight = new double[n + 3][n + 2];
		root = new int[n + 2][n + 1];

		// init expect[i][i-1] and weight[i][i-1]
		for (int i = 1; i < n + 1 + 1; i++) {
			expect[i][i - 1] = q[i - 1];
			weight[i][i - 1] = q[i - 1];
		}

		//
		for (int l = 1; l < n + 1; l++) {
			for (int i = 1; i < n - l + 1 + 1; i++) {
				int j = i + l - 1;
				expect[i][j] = Float.MAX_VALUE;
				weight[i][j] = weight[i][j - 1] + p[j] + q[j];

				for (int r = i; r < j + 1; r++) {
					double t = expect[i][r - 1] + expect[r + 1][j]
							+ weight[i][j];
					if (t < expect[i][j]) {
						expect[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
	}

	public void optimalBSTBetter(double[] p, double[] q, int n) {
		expect = new double[n + 3][n + 2];
		weight = new double[n + 3][n + 2];
		root = new int[n + 2][n + 1];

		// init expect[i][i-1] and weight[i][i-1]
		for (int i = 1; i < n + 1 + 1; i++) {
			expect[i][i - 1] = q[i - 1];
			weight[i][i - 1] = q[i - 1];
		}

		for (int i = 1; i < n + 1; i++) {
			int j = i;
			expect[i][j] = Float.MAX_VALUE;
			weight[i][j] = weight[i][j - 1] + p[j] + q[j];

			for (int r = i; r < j + 1; r++) {
				double t = expect[i][r - 1] + expect[r + 1][j] + weight[i][j];
				if (t < expect[i][j]) {
					expect[i][j] = t;
					root[i][j] = r;
				}
			}
		}

		//
		for (int l = 2; l < n + 1; l++) {
			for (int i = 1; i < n - l + 1 + 1; i++) {
				int j = i + l - 1;
				expect[i][j] = Float.MAX_VALUE;
				weight[i][j] = weight[i][j - 1] + p[j] + q[j];
				//root[i][j-1] <= root[i,j] <= root[i+1,j]
				int rootBegin = Math.max(root[i][j-1], i);
				int rootEnd = Math.min(root[i+1][j], j);
				for (int r = rootBegin; r < rootEnd + 1; r++) {
					double t = expect[i][r - 1] + expect[r + 1][j]
							+ weight[i][j];
					if (t < expect[i][j]) {
						expect[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
	}
	
	/**
	 * 打印最优二叉搜索树的结构
	 * 
	 * @param n
	 */
	public void printBST(int n) {
		int top = root[1][n];
		// System.out.println(1+":"+n);
		System.out.println("K" + top + " is root");
		int begin = 1;
		int end = n;
		if (top > begin) {
			printSubBST(1, top - 1, top, true);
		} else if (top == begin) {
			System.out.println("d" + (top - 1) + " is K" + begin
					+ "'s left child");
		}
		if (top < end) {
			printSubBST(top + 1, end, top, false);
		} else if (top == end) {
			System.out.println("d" + top + " is K" + end + "'s right child");
		}
	}

	/**
	 * 打印子树的节点
	 * 
	 * @param begin
	 * @param end
	 * @param parent
	 * @param isLeft
	 *            是否为左子树
	 */
	private void printSubBST(int begin, int end, int parent, boolean isLeft) {
		int top = root[begin][end];
		String tail = null;
		if (isLeft) {
			tail = "'s left child";
		} else {
			tail = "'s right child";
		}

		System.out.println("K" + top + " is K" + parent + tail);
		if (top > begin) {
			printSubBST(begin, top - 1, top, true);
		} else if (top == begin) {
			System.out.println("d" + (top - 1) + " is K" + begin
					+ "'s left child");
		}
		if (top < end) {
			printSubBST(top + 1, end, top, false);
		} else if (top == end) {
			System.out.println("d" + top + " is K" + end + "'s right child");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BST bst = new BST();

		// double[] p = { 0, 0.15, 0.10, 0.05, 0.10, 0.20 };
		// double[] q = { 0.05, 0.10, 0.05, 0.05, 0.05, 0.10 };
		double[] p = { 0, 0.04, 0.06, 0.03, 0.02, 0.10, 0.12, 0.09 };
		double[] q = { 0.36, 0.06, 0.06, 0.06, 0.00, 0.00, 0.00, 0.00 };
		int n = p.length - 1;
		bst.optimalBSTBetter(p, q, n);
		bst.printBST(n);
		bst.optimalBST(p, q, n);
		bst.printBST(n);
	}

}
