/**
 * @projectName Algorithm
 * @package DoublePoint.LeetCode18
 * @className DoublePoint.LeetCode18.FourSum
 */
package DoublePoint.LeetCode18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FourSum
 * @description 18. 四数之和
 * @author SongJian
 * @date 2022/11/19 09:10
 * @version
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        int p1, p2, p3, p4;
        // 遍历取第一个数
        for (p1 = 0; p1 <= len - 4; p1++) {
            // 去重判断
            if (p1 > 0 && nums[p1] == nums[p1 - 1]) continue;
            // 剪枝
            if ((long) nums[p1] + (long) nums[p1+1] + (long) nums[p1+2] + (long) nums[p1+3] > target) {
                break;
            }
            if ((long) nums[p1] + (long) nums[len-1] + (long) nums[len-2] + (long) nums[len-3] < target) {
                continue;
            }
            // 遍历取第二个数
            for (p2 = p1 + 1; p2 <= len - 3; p2++) {
                // 去重判断
                if (p2 > p1 + 1 && nums[p2] == nums[p2 - 1]) {
                    continue;
                }
                // 剪枝
                if ((long) nums[p1] + (long) nums[p2] + (long) nums[p2 + 1] + (long) nums[p2 + 2] > target) {
                    break;
                }
                if ((long) nums[p1] + (long) nums[p2] + (long) nums[len - 1] + (long) nums[len - 2] < target) {
                    continue;
                }
                // 双指针进行第三层循环
                p3 = p2 + 1;
                p4 = len - 1;
                while (p3 < p4) {
                    if ((long) nums[p1] + (long) nums[p2] + (long) nums[p3] + (long) nums[p4] == target) {
                        // 添加结果
                        res.add(Arrays.asList(nums[p1], nums[p2], nums[p3], nums[p4]));
                        // 去重
                        while (p3 < p4 && nums[p3] == nums[p3 + 1]) p3++;
                        while (p3 < p4 && nums[p4] == nums[p4 - 1]) p4--;
                        p3++;
                        p4--;
                    } else if ((long) nums[p1] + (long) nums[p2] + (long) nums[p3] + (long) nums[p4] < target) {
                        p3++;
                    } else {
                        p4--;
                    }
                }
            }
        }
        return res;
    }
}
 
