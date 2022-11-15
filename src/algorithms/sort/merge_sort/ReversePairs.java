/**
 * @projectName Algorithm
 * @package algorithms.sort.merge_sort
 * @className algorithms.sort.merge_sort.reversePairs
 */
package algorithms.sort.merge_sort;

/**
 * reversePairs
 * @description 逆序对
 * @author SongJian
 * @date 2022/11/15 19:33
 * @version
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        return process(nums, 0, nums.length - 1);
    }

    public int process (int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int middle = l + ((r - l) >> 1);
        return process(nums, l, middle) + process(nums, middle + 1, r)
                + merge(nums, l, middle, r);
    }

    public int merge (int[] nums, int l, int middle, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = middle + 1;
        int ans = 0;
        while (p1 <= middle && p2 <= r) {
            // 如果左组大，则记录逆序
            ans += nums[p1] > nums[p2] ? middle - p1 + 1 : 0;
            // 注意，相等时，先放左组
            help[i++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= middle) {
            help[i++] = nums[p1++];
        }
        while (p2 <= r) {
            help[i++] = nums[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            nums[l + j] = help[j];
        }
        return ans;
    }
}
 
