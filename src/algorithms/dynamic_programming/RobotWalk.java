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
     */


    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
    }
}
 
