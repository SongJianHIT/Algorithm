/**
 * @projectName Algorithm
 * @package DoublePoint.LeetCode15
 * @className DoublePoint.LeetCode15.ThreeSum
 */
package DoublePoint.LeetCode15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ThreeSum
 * @description 15三数和
 * @author SongJian
 * @date 2022/11/17 09:27
 * @version
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;

        // 数组排序
        Arrays.sort(nums);

        // 循环
        for (int i = 0; i < nums.length; i++) {
            // nums[i] 为标志元素，需要保证：
            //    -> nums[i] != nums[i-1]，去重
            //    -> nums[i] <= 0，若大于0，就没有遍历的必要了
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;

            // 左右指针
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    // 符合条件
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 找到重复元素边界
                    while (L < R && nums[L + 1] == nums[L]) L++;
                    while (L < R && nums[R - 1] == nums[R]) R--;
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }
}
 
