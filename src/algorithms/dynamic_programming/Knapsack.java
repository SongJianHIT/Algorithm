/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.Knapsack
 */
package algorithms.dynamic_programming;

/**
 * Knapsack
 * @description  背包问题
 * @author SongJian
 * @date 2022/12/20 10:30
 * @version
 */
public class Knapsack {

    /**
     * ==============================================================================================================
     * @title maxValue 暴力递归
     * @author SongJian
     * @param: w 物品的重量
     * @param: v 物品的价值
     * @param: bag 背包的容量
     * @updateTime 2022/12/20 10:37
     * @return: int
     * @throws
     * @description 背包问题，返回不超重的情况下，能够得到的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数
        return process(w, v, 0, bag);
    }

    /**
     * 当前考虑到了 index 号货物，index...所有货物可以自由选择
     * 所做的选择不能超过背包容量，返回最大价值
     * @param w
     * @param v
     * @param index
     * @param bag
     * @return
     */
    public static int process(int[] w, int[] v, int index, int bag) {
        if (index == w.length) {
            // base case
            return 0;
        }
        // 还有货、有空间
        // case 1：不要当前货
        int p1 = process(w, v, index + 1, bag);
        // case 2：要当前的货
        int p2 = 0;
        if (bag - w[index] >= 0) {
            // 当前的选择需要保证是有效的
            p2 = v[index] + process(w, v, index + 1, bag - w[index]);
        }
        return Math.max(p1, p2);
    }

    /**
     * ==============================================================================================================
     * @title dp 动态规划方法
     * @author SongJian
     * @param: w
     * @param: v
     * @param: bag
     * @updateTime 2022/12/20 11:45
     * @return: int
     * @throws
     * @description 动态规划只是暴力递归的缓存
     */
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        // index: 0 ~ N
        // rest: 0 ~ bag
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    /**
     * ==============================================================================================================
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
