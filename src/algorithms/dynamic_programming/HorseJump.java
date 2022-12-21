/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.HorseJump
 */
package algorithms.dynamic_programming;

/**
 * HorseJump
 * @description 象棋马走日
 * @author SongJian
 * @date 2022/12/21 16:16
 * @version
 */
public class HorseJump {

    /**
     * ==============================================================================================================
     * 暴力递归
     * @title jump
     * @author SongJian
     * @param: a
     * @param: b
     * @param: k
     * @updateTime 2022/12/21 16:17
     * @return: int
     * @throws
     * @description 从 (0, 0) 出发，跳 k 步，最终落在 (a, b) 上的方法数
     */
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    /**
     * 从 (x, y) 出发，还剩 rest 步需要跳，最终到达 (a, b) 的方法数量
     * @param x
     * @param y
     * @param rest
     * @param a
     * @param b
     * @return
     */
    public static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            // base case：之前做的尝试的结果
            return (x == a && y == b) ? 1 : 0;
        }
        int ans = process(x + 2, y + 1, rest - 1, a, b);
        ans += process(x + 2, y - 1, rest - 1, a, b);
        ans += process(x + 1, y + 2, rest - 1, a, b);
        ans += process(x + 1, y - 2, rest - 1, a, b);
        ans += process(x - 1, y + 2, rest - 1, a, b);
        ans += process(x - 1, y - 2, rest - 1, a, b);
        ans += process(x - 2, y + 1, rest - 1, a, b);
        ans += process(x - 2, y - 1, rest - 1, a, b);
        return ans;
    }

    /**
     * ==============================================================================================================
     * 动态规划
     * @title dp
     * @author SongJian
     * @param: a
     * @param: b
     * @param: k
     * @updateTime 2022/12/21 16:55
     * @return: int
     * @throws
     * @description
     */
    public static int dp(int a, int b , int k) {
        int[][][] dp = new int[10][9][k+1];
        // 填好第 0 层
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; ++x) {
                for (int y = 0; y < 9; ++y) {
                    int ans = pick(dp, x + 2, y + 1, rest - 1);
                    ans += pick(dp, x + 2, y - 1, rest - 1);
                    ans += pick(dp, x + 1, y - 2, rest - 1);
                    ans += pick(dp, x + 1, y + 2, rest - 1);
                    ans += pick(dp, x - 2, y - 1, rest - 1);
                    ans += pick(dp, x - 1, y - 2, rest - 1);
                    ans += pick(dp, x - 1, y + 2, rest - 1);
                    ans += pick(dp, x - 2, y + 1, rest - 1);
                    dp[x][y][rest] = ans;
                }
            }
        }
        return dp[0][0][k];
    }

    /**
     * 在 dp 表中，取 (x,y,rest) 的值，如果越界返回0
     * @param dp
     * @param x
     * @param y
     * @return
     */
    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }


    /**
     * ==============================================================================================================
     * 测试
     */
    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
    }

}













