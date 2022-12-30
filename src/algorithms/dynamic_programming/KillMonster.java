/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.KillMonster
 */
package algorithms.dynamic_programming;

/**
 * KillMonster
 * @description 打怪兽
 * @author SongJian
 * @date 2022/12/30 09:51
 * @version
 */
public class KillMonster {

    /**
     * =================================================================================================================
     * 暴力递归
     * @title right
     * @author SongJian
     * @param: N
     * @param: M
     * @param: K
     * @updateTime 2022/12/30 09:53
     * @return: double
     * @throws
     * @description
     */
    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = process1(N, M, K);
        return (double) ((double) kill / (double) all);
    }

    /**
     * @param N 怪兽还剩的血量
     * @param M 每次的伤害
     * @param K 还有K次可以砍
     * @return 砍死的情况数
     */
    public static long process1(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }
        long ways = 0;
        // 等概率掉血
        for (int i = 0; i <= M; i++) {
            ways += process1(N - i, M, K - 1);
        }
        return ways;
    }

    /**
     * =================================================================================================================
     * 动态规划
     * @title dp1
     * @author SongJian
     * @param: N
     * @param: M
     * @param: K
     * @updateTime 2022/12/30 10:00
     * @return: double
     * @throws
     * @description
     */
    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        // 填第 0 行的值
        dp[0][0] = 1;
        for (int k = 1; k <= K; ++k) {
            // 直接计算生成点
            dp[k][0] = (long) Math.pow(M + 1, k);
            for (int n = 1; n <= N; ++n) {
                long ways = 0;
                // 有枚举行为，可以继续优化
                for (int i = 0; i <= M; ++i) {
                    if (n - i >= 0) {
                        ways += dp[k - 1][n - i];
                    } else {
                        // 直接计算生存点
                        ways += Math.pow(M + 1, k - 1);
                    }
                }
                dp[k][n] = ways;
            }
        }
        return (double) ((double) dp[K][N] / (double) all);
    }


    /**
     * =================================================================================================================
     * 对枚举行为优化，动态规划
     * @title dp2
     * @author SongJian
     * @param: N
     * @param: M
     * @param: K
     * @updateTime 2022/12/30 10:28
     * @return: double
     * @throws
     * @description
     */
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        // 填第 0 行的值
        dp[0][0] = 1;
        for (int k = 1; k <= K; ++k) {
            // 直接计算生成点
            dp[k][0] = (long) Math.pow(M + 1, k);
            for (int n = 1; n <= N; ++n) {
                // 对枚举行为优化
                dp[k][n] = dp[k][n - 1] + dp[k - 1][n];
                if (n - 1 - M >= 0) {
                    dp[k][n] -= dp[k - 1][n - 1 - M];
                } else {
                    dp[k][n] -= Math.pow(M + 1, k - 1);
                }
            }
        }
        return (double) ((double) dp[K][N] / (double) all);
    }

    /**
     * =================================================================================================================
     * test
     */
    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

