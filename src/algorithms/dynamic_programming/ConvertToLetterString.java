/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.ConvertToLetterString
 */
package algorithms.dynamic_programming;

/**
 * ConvertToLetterString 数字字符转字符串
 * @description
 * @author SongJian
 * @date 2022/12/20 11:57
 * @version
 */
public class ConvertToLetterString {

    /**
     * ==============================================================================================================
     * @title number
     * @author SongJian
     * @param: str 只含有数字字符 0 - 9
     * @updateTime 2022/12/20 12:00
     * @return: int 多少种转化方案
     * @throws
     * @description
     */
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    /**
     * 从 String[index] 开始去转化，返回有多少种转化方法
     * @param chars
     * @param index
     * @return
     */
    public static int process(char[] chars, int index) {
        if (index == chars.length) {
            // 来到了末尾，说明找到了答案
            return 1;
        }
        if (chars[index] == '0') {
            // 对应是从1开始的，如果单独遇到了0，说明前面的决定是无效的
            return 0;
        }
        // case 1：让 index 单独转化，看后续有多少有效
        int ways = process(chars, index + 1);
        // case 2：让 index 与 index + 1 共同构成字母，但需要判断
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + chars[index + 1] - '0' < 27) {
            ways += process(chars, index + 2);
        }
        return ways;
    }

    /**
     * ==============================================================================================================
     * @title dp 动态规划
     * @author SongJian
     * @param: s
     * @updateTime 2022/12/20 12:21
     * @return: int
     * @throws
     * @description
     */
    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            // 如果为`0`就跳过
            if (chars[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < N && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    /**
     * ==============================================================================================================
     * 测试
     */

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);
            int ans2 = number(s);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
