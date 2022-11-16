/**
 * @projectName Algorithm
 * @package DoublePoint.LeetCode11
 * @className DoublePoint.LeetCode11.MaxArea
 */
package DoublePoint.LeetCode11;

/**
 * MaxArea
 * @description LeetCode11：盛最多水的容器
 * @author SongJian
 * @date 2022/11/16 08:49
 * @version 双指针解法
 */
public class MaxArea {
    public int maxArea(int[] height) {
        // 左右指针，逐渐向内移
        int l = 0;
        int r = height.length - 1;
        // 统计结果
        int res = 0;
        while (l < r) {
            res = Math.max(res, (r - l) * Math.min(height[r], height[l]));
            // 注意：只移动指向数字小的指针！
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}