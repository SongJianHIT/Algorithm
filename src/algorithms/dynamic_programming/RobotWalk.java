/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.RobotWalk
 */
package algorithms.dynamic_programming;

/**
 * RobotWalk
 * @description
 * @author SongJian
 * @date 2022/12/19 08:19
 * @version
 */
public class RobotWalk {

    /**
     * ==============================================================================================================
     * 方法一：符合直觉的暴力递归
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    private static int ways1 (int N, int start, int aim, int K) {
        return process1(start, K, aim, N);
    }

    /**
     * 递归过程，机器人从 cur 出发，走过 rest 步后，最终停在 aim 位置的方法树，是多少？
     * @param cur
     * @param rest
     * @param aim
     * @param N
     * @return
     */
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            // 剩余步数为0，看看在不在目标上
            return cur == aim ? 1 : 0;
        }
        // 在调用代码的阶段，避免了非法情况
        if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        }
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    /**
     * ==============================================================================================================
     * 方法二：在方法一的基础上，增加缓存
     *         记忆化搜索
     * @title ways2
     * @author SongJian
     * @param: N
     * @param: start
     * @param: aim
     * @param: K
     * @updateTime 2022/12/19 08:55
     * @return: int
     * @throws
     * @description
     */
    private static int ways2 (int N, int start, int aim, int K) {
        // 缓存表
        int[][] dp = new int[N+1][K+1];
        // 如果 dp[cur][rest]=-1，则表示 process2(cur, rest) 没算过
        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= K; ++j) {
                dp[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            // 缓存命中
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            // 剩余步数为0，看看在不在目标上
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans =  process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        // 放入缓存
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * ==============================================================================================================
     * 最终动态规划版本
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int ways3 (int N, int start, int aim, int K) {
        int[][] dp = new int[N+1][K+1];
        dp[aim][0] = 1;
        // 列
        for (int rest = 1; rest <= K; ++rest) {
            // 行
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; ++cur) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    /**
     * ==============================================================================================================
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
 
