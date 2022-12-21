/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.LongestPalindromeSubseq
 */
package algorithms.dynamic_programming;

import java.util.Map;

/**
 * LongestPalindromeSubseq
 * @description 最长回文子序列
 * @author SongJian
 * @date 2022/12/21 14:02
 * @version
 */
public class LongestPalindromeSubseq {

    /**
     * ==============================================================================================================
     * 暴力递归
     * @title longestPalindromeSubseq
     * @author SongJian
     * @param: s
     * @updateTime 2022/12/21 14:07
     * @return: int
     * @throws
     * @description 求字符串 s 的最长回文子序列的长度
     */

    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        // 尝试函数
        return f(chars, 0, s.length() - 1);
    }

    /**
     * 范围尝试模型，返回 str 在 [L...R] 上的最长回文子序列长度
     *
     * 范围尝试模型：特别在于讨论开头如何，结尾如何！
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    public static int f(char[] str, int L, int R) {
        if (L == R) {
            // base case : 只有一个字符，肯定是回文
            return 1;
        }
        if (L == R - 1) {
            // base case : 两个字符，相等就返回2，否则为1
            return str[L] == str[R] ? 2 : 1;
        }
        // case 1 : 最长回文子序列 不以 L 开头，也不以 R 结尾
        int c1 = f(str, L + 1, R - 1);
        // case 2 : 最长回文子序列 以 L 开头，不以 R 结尾
        int c2 = f(str, L, R - 1);
        // case 3 : 最长回文子序列 不以 L 开头，以 R 结尾
        int c3 = f(str, L + 1, R);
        // case 4 : 最长回文子序列 以 L 开头，以 R 结尾
        // 这种情况，需要保证 str[L] = str[R] 才行
        int c4 = str[L] == str[R] ? 2 + f(str, L + 1, R - 1) : 0;

        // 返回最长长度
        return Math.max(c1,  Math.max(c2, Math.max(c3, c4)));
    }

    /**
     * ==============================================================================================================
     * 傻缓存法
     * @title longestPalindromeSubseq1
     * @author SongJian
     * @param: s
     * @updateTime 2022/12/21 14:22
     * @return: int
     * @throws
     * @description
     */

    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[][] map = new int[s.length()][s.length()];
        // 尝试函数
        return f1(chars, 0, s.length() - 1, map);
    }

    public static int f1(char[] str, int L, int R, int[][] map) {
        if (map[L][R] != 0) {
            return map[L][R];
        }
        if (L == R) {
            // base case : 只有一个字符，肯定是回文
            return 1;
        }
        if (L == R - 1) {
            // base case : 两个字符，相等就返回2，否则为1
            return str[L] == str[R] ? 2 : 1;
        }
        // case 1 : 最长回文子序列 不以 L 开头，也不以 R 结尾
        int c1 = f1(str, L + 1, R - 1, map);
        // case 2 : 最长回文子序列 以 L 开头，不以 R 结尾
        int c2 = f1(str, L, R - 1, map);
        // case 3 : 最长回文子序列 不以 L 开头，以 R 结尾
        int c3 = f1(str, L + 1, R, map);
        // case 4 : 最长回文子序列 以 L 开头，以 R 结尾
        // 这种情况，需要保证 str[L] = str[R] 才行
        int c4 = str[L] == str[R] ? 2 + f1(str, L + 1, R - 1, map) : 0;

        // 返回最长长度
        int ans = Math.max(c1,  Math.max(c2, Math.max(c3, c4)));
        map[L][R] = ans;
        return ans;
    }

    /**
     * ==============================================================================================================
     * 动态规划 1
     * @title longestPalindromeSubseq2
     * @author SongJian
     * @param: s
     * @updateTime 2022/12/21 14:27
     * @return: int
     * @throws
     * @description
     */
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        int[][] dp = new int[N][N];
        char[] str = s.toCharArray();
        dp[N -1][N -1] = 1;
        for (int i = 0; i < N - 1; ++i) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 2; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int c1 = dp[L + 1][R - 1];
                int c2 = dp[L][R - 1];
                int c3 = dp[L + 1][R];
                int c4 = str[L] == str[R] ? 2 + dp[L + 1][R - 1] : 0;
                dp[L][R] = Math.max(c1,  Math.max(c2, Math.max(c3, c4)));
            }
        }
        return dp[0][N - 1];
    }

    /**
     * ==============================================================================================================
     * 动态规划 2
     * @title longestPalindromeSubseq2
     * @author SongJian
     * @param: s
     * @updateTime 2022/12/21 14:27
     * @return: int
     * @throws
     * @description
     */
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        int[][] dp = new int[N][N];
        char[] str = s.toCharArray();
        dp[N -1][N -1] = 1;
        for (int i = 0; i < N - 1; ++i) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 2; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                // 优化，不需要依赖左下
                // 只依赖 左、下 和 （在相等时）左下
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}









