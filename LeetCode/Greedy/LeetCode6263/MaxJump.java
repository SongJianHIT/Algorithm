/**
 * @projectName Algorithm
 * @package Greedy.LeetCode6263
 * @className Greedy.LeetCode6263.MaxJump
 */
package Greedy.LeetCode6263;

/**
 * MaxJump
 * @description
 * @author SongJian
 * @date 2022/12/11 08:56
 * @version
 */
public class MaxJump {
    public int maxJump(int[] stones) {
        int len = stones.length;
        int ans = stones[1] - stones[0];
        for (int i = 2; i < len; ++i) {
            // 每次都是间隔跳，结果必出在其中
            ans = Math.max(ans, stones[i] - stones[i - 2]);
        }
        return ans;
    }
}
 
