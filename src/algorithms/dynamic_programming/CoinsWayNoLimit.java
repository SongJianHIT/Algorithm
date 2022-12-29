/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.CoinsWayNoLimit
 */
package algorithms.dynamic_programming;

/**
 * CoinsWayNoLimit
 * @description 凑钱2
 * @author SongJian
 * @date 2022/12/29 13:01
 * @version
 */
public class CoinsWayNoLimit {

    /**
     * ================================================================================================================
     * 从左往右尝试模型，暴力递归
     * @title coinsWay
     * @author SongJian
     * @param: arr
     * @param: aim
     * @updateTime 2022/12/29 13:10
     * @return: int
     * @throws
     * @description
     */
    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    /**
     * arr[index...] 所有的面值，每一个面值都可以任意选择张数，组成正好 rest 这么多钱的方法数
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 尝试不同张数
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    /**
     * ================================================================================================================
     * 动态规划
     * 本题，求一个格子，需要 O(n) 的时间复杂度
     * @title dp
     * @author SongJian
     * @param: arr
     * @param: aim
     * @updateTime 2022/12/29 13:10
     * @return: int
     * @throws
     * @description
     */
    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N -1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 尝试不同张数
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * ================================================================================================================
     * 优化，省去 for 循环！
     * 使用严格表结构优化
     * @title dp2
     * @author SongJian
     * @param: arr
     * @param: aim
     * @updateTime 2022/12/29 13:54
     * @return: int
     * @throws
     * @description
     */
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

