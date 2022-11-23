/**
 * @projectName Algorithm
 * @package Dynamic_Programming.LeetCode45
 * @className Dynamic_Programming.LeetCode45.Jump
 */
package Dynamic_Programming.LeetCode45;

/**
 * Jump
 * @description 45. 跳跃游戏 II
 * @author SongJian
 * @date 2022/11/23 09:32
 * @version
 */
public class Jump {

    /**
     *  方法一：动态规划
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] dp = new int[nums.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[dp.length - 1];
    }

    /**
     * 方法二：正向搜索，贪心
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        // start, end 分别记录跳跃点的范围
        int start = 0, end = 1, steps = 0;
        while (end < nums.length) {
            // 记录当前跳跃点区间能够到达最远的距离
            int maxPos = 0;
            for (int i = start; i < end; i++) {
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end;
            end = maxPos + 1;
            steps++;
        }
        return steps;
    }
}
 
