/**
 * @projectName Algorithm
 * @package algorithms.matrix_quickPower
 * @className algorithms.matrix_quickPower.FibonacciProblem
 */
package algorithms.matrix_quickPower;

/**
 * FibonacciProblem
 * @description 斐波那契数列的矩阵快速幂方法
 * @author SongJian
 * @date 2023/1/3 14:32
 * @version
 */
public class FibonacciProblem {


    /**
     * @title f1
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 19:02
     * @return: int
     * @throws
     * @description 递归方式求解斐波那契
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * @title f2
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 19:03
     * @return: int
     * @throws
     * @description 动态规划求解斐波那契
     */
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * @title f3
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 15:38
     * @return: int
     * @throws
     * @description 矩阵快速幂，O(logN)
     */
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[0][1];
    }

    /**
     * @title matrixPower
     * @author SongJian
     * @param: m
     * @param: p
     * @updateTime 2023/1/3 18:53
     * @return: int[][]
     * @throws
     * @description 求矩阵 m 的 p 次方
     */
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; ++i) {
            // 单位阵
            res[i][i] = 1;
        }
        int[][] t = m;
        // 快速幂
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    /**
     * @title product
     * @author SongJian
     * @param: a
     * @param: b
     * @updateTime 2023/1/3 18:56
     * @return: int[][]
     * @throws
     * @description 矩阵乘法
     */
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int c = 0; c < k; ++c) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 19;

        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

    }
}

