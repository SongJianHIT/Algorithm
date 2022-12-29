/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.BobDie
 */
package algorithms.dynamic_programming;

/**
 * BobDie
 * @description 醉汉行走
 * @author SongJian
 * @date 2022/12/29 19:07
 * @version
 */
public class BobDie {

    /**
     * @title livePosibility1
     * @author SongJian
     * @param: row
     * @param: col
     * @param: k
     * @param: N
     * @param: M
     * @updateTime 2022/12/29 19:08
     * @return: double
     * @throws
     * @description 醉汉行走
     */
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        // 总共的可能性是 4^k
        // 生存点数 process 计算出
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    /**
     * =================================================================================================================
     * 暴力递归
     * @title process
     * @author SongJian
     * @param: row
     * @param: col
     * @param: rest
     * @param: N
     * @param: M
     * @updateTime 2022/12/29 19:09
     * @return: long
     * @throws
     * @description 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
     */
    public static long process(int row, int col, int rest, int N, int M) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 还在棋盘中！
        if (rest == 0) {
            return 1;
        }
        // 还在棋盘中！还有步数要走
        long up = process(row - 1, col, rest - 1, N, M);
        long down = process(row + 1, col, rest - 1, N, M);
        long left = process(row, col - 1, rest - 1, N, M);
        long right = process(row, col + 1, rest - 1, N, M);
        return up + down + left + right;
    }

    /**
     * =================================================================================================================
     * 动态规划，三维
     * @title livePosibility2
     * @author SongJian
     * @param: row
     * @param: col
     * @param: k
     * @param: N
     * @param: M
     * @updateTime 2022/12/29 19:11
     * @return: double
     * @throws
     * @description
     */
    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    dp[i][j][rest] = pick(dp, N, M, i - 1, j, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i + 1, j, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i, j - 1, rest - 1);
                    dp[i][j][rest] += pick(dp, N, M, i, j + 1, rest - 1);
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }
    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }
}

