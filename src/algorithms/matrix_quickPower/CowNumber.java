/**
 * @projectName Algorithm
 * @package algorithms.matrix_quickPower
 * @className algorithms.matrix_quickPower.CowNumber
 */
package algorithms.matrix_quickPower;

/**
 * CowNumber
 * @description
 * @author SongJian
 * @date 2023/1/3 21:08
 * @version
 */
public class CowNumber {


    /**
     * ==============================================================================================================
     * 方法一：暴力递归
     * @title c1
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 21:45
     * @return: int
     * @throws
     * @description
     */
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    /**
     * ==============================================================================================================
     * 方法二：动态规划
     * @title c2
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 21:45
     * @return: int
     * @throws
     * @description
     */
    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }


    /**
     * ==============================================================================================================
     * 方法三：矩阵快速幂
     * @title cowNumber
     * @author SongJian
     * @param: n
     * @updateTime 2023/1/3 21:34
     * @return: int
     * @throws
     * @description 第 N 年后的牛的数量
     */
    public static int cowNumber(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 3) {
            return n;
        }
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static int[][] matrixPower(int[][] base, int p) {
        int[][] ans = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; ++i) {
            ans[i][i] = 1;
        }
        int[][] t = base;
        for (; p > 0; p >>= 1) {
            if ((p & 1) != 0) {
                ans = product(ans, t);
            }
            t = product(t, t);
        }
        return ans;
    }

    public static int[][] product (int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int l = a[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < l; ++k) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 19;

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(cowNumber(n));
        System.out.println("===");

    }
}

