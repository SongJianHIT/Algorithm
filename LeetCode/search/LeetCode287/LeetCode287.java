/**
 * @projectName Algorithm
 * @package search
 * @className search.LeetCode287.LeetCode287
 */
package search.LeetCode287;

/**
 * LeetCode287
 * @description https://leetcode.cn/problems/find-the-duplicate-number
 * @author SongJian
 * @date 2022/12/14 08:49
 * @version
 */
public class LeetCode287 {
    /**
     * 二分搜索方法
     * 搜索是对 cnt 进行搜索！！！！而不是对数组！！！！
     * cnt[i] 记录了原数组中小于等于 i 的数字个数，它符合递增的情况
                 * 当 cnt[i] > i 时，说明重复元素出现在 i 或者 i 之前
                 * 当 cnt[i] <= i 时，说明 i 和 i 之前元素都没有重复
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        // 1...n 是 cnt 的范围
        int l = 1, r = n, mid = 0;
        int ans = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (mid < cnt) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 注意返回的 i ，而不是 nums[i]
        return l;
    }

    /**
     * 快慢指针，将数组看成连标，找到第一个入环节点即是重复元素
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int f = 0;
        int s = 0;
        f = nums[nums[f]];
        s = nums[s];
        while (s != f) {
            s = nums[s];
            f = nums[nums[f]];
        }
        f = 0;
        while (s != f) {
            s = nums[s];
            f = nums[f];
        }
        return f;
    }
}
 
