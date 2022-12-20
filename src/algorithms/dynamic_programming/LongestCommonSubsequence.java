/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.LongestCommonSubsequence
 */
package algorithms.dynamic_programming;

/**
 * LongestCommonSubsequence
 *
 * @author SongJian
 * @description https://leetcode.cn/problems/longest-common-subsequence/
 * @date 2022/12/20 16:55
 */
public class LongestCommonSubsequence {

    /**
     * ==============================================================================================================
     * 暴力递归
     * @title longestCommonSubsequence
     * @author SongJian
     * @param: s1
     * @param: s2
     * @updateTime 2022/12/20 17:18
     * @return: int
     * @throws
     * @description
     */

    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        // 尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    /**
     * 求 str[0....i] 与 str2[0....j] 的公共子序列个数
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            // base case
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            // str1 只有一个字符
            if (str2[j] == str1[i]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            // str2 只有一个字符
            if (str2[j] == str1[i]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            // str1 和 str2 都大于一个字符
            // 样本对应模型：只与最后一个字符讨论
            // 最长的公共子序列是否包含末尾元素 str1[i] 和 str2[j]
            // case 1：完全不考虑i，考虑j
            int p1 = process1(str1, str2, i - 1, j);
            // case 2：考虑i，完全不考虑j
            int p2 = process1(str1, str2, i, j - 1);
            // case 3：考虑i，考虑j
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }

    /**
     * ==============================================================================================================
     * 动态规划
     * @title longestCommonSubsequence1
     * @author SongJian
     * @param: s1
     * @param: s2
     * @updateTime 2022/12/20 17:19
     * @return: int
     * @throws
     * @description
     */
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}

