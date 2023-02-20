# LCS与LIS

> **子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。

## 1 最长公共子序列 LCS

> 【最长公共子序列】
>
> LeetCode: https://leetcode.cn/problems/longest-common-subsequence/description/

求两个数组或者字符串的最长公共子序列问题，肯定是要用 **动态规划** 的。

动态规划也是有套路的：

- **单个数组** 或者 **字符串** 要用动态规划时，可以把动态规划 `dp[i]` 定义为 `nums[0:i]` 中想要求的结果；
- 当 **两个数组** 或者 **字符串** 要用动态规划时，可以把动态规划定义成两维的 `dp[i][j]` ，其含义是在 `A[0:i]` 与 `B[0:j]` 之间匹配得到的想要的结果。

**状态定义**：定义 `dp[i][j]` 表示 `text1[0:i-1]` 和 `text2[0:j-1]` 的最长公共子序列。

知道状态定义之后，我们开始写 **状态转移** 方程。

- 当 `text1[i - 1] == text2[j - 1]` 时，说明两个子字符串的最后一位相等，所以最长公共子序列又增加了 1，所以 dp[i][j] = dp[i - 1][j - 1] + 1；举个例子，比如对于 ac 和 bc 而言，他们的最长公共子序列的长度等于 a 和 b 的最长公共子序列长度 0 + 1 = 1。
- 当 `text1[i - 1] != text2[j - 1]` 时，说明两个子字符串的最后一位不相等，那么此时的状态 `dp[i][j]` 应该是 `dp[i - 1][j]` 和 `dp[i][j - 1]` 的最大值。

由于 `dp[i][j]` 依赖与 `dp[i - 1][j - 1]` , `dp[i - 1][j]`, `dp[i][j - 1]`，所以 i 和 j 的遍历顺序肯定是从小到大的。

```java
class Solution {
    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n = chars1.length, m = chars2.length;
        // dp[i][j]：s1[0:i-1]，s2[0:j-1] 的最长公共子序列长度
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
```

- 时间复杂度：$O(MN)$ 
- 空间复杂度：$O(MN)$ 

## 2 最长上升子序列 LIS

> 【最长递增子序列】
>
> LeetCode: https://leetcode.cn/problems/longest-increasing-subsequence/

## 3 LCS 与 LIS 转化

> 【得到子序列的最少操作次数】
>
> LeetCode: https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence/description/

因此从题面来说，这是一道最长公共子序列问题（LCS）。

但朴素求解 LCS 问题复杂度为 $O(n * m)$ ，使用状态定义 `f[i][j]` 为考虑 a 数组的前 i 个元素和 b 数组的前 j 个元素的最长公共子序列长度为多少进行求解。

而本题的数据范围为 $10^5$ ，使用朴素求解 LCS 的做法必然超时。

一个很显眼的切入点是 **target 数组元素各不相同**，当 LCS 问题增加某些条件限制之后，会存在一些很有趣的性质。

其中一个经典的性质就是：**当其中一个数组元素各不相同时，最长公共子序列问题（LCS）可以转换为最长上升子序列问题（LIS）进行求解** 。同时最长上升子序列问题（LIS）存在使用**「维护单调序列 + 二分」**的贪心解法，复杂度为 $O(nlog⁡n)$ 。

```java
class Solution {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        int m = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            if (map.containsKey(arr[i])) {
                list.add(map.get(arr[i]));
            }
        }
        // 转化为 LIS 问题
        int len = list.size(), max = 0;
        // f[i]：以 i 为结尾，最长上升子序列长度  --- dp 数组
        // g[i]：长度为 i 的子序列，最小的结尾元素  ---- 贪心数组
        int[] f = new int[len + 1], g = new int[len + 1];
        // 初始化 g
        Arrays.fill(g, Integer.MAX_VALUE);
        for (int i = 0; i < len; ++i) {
            int tmp = list.get(i);
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < tmp) l = mid;
                else r = mid - 1;
            }
            int clen = r + 1;
            f[i] = clen;
            g[clen] = Math.min(g[clen], tmp);
            max = Math.max(max, f[i]);
        }
        return n - max;
    }   
}
```













