/**
 * @projectName Algorithm
 * @package algorithms.sort.merge_sort
 * @className algorithms.sort.merge_sort.CountRangeSum
 */
package algorithms.sort.merge_sort;

/**
 * CountRangeSum
 * @description 区间和个数
 *              https://leetcode.cn/problems/count-of-range-sum/description/
 * @author SongJian
 * @date 2022/11/16 16:07
 * @version
 */
public class CountRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 求前缀和数组，sum[]
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return count(sum, 0, nums.length - 1, lower, upper);
    }


    /**
     * @title count
     * @author SongJian
     * @param: sum -> 前缀和数组
     * @param: L
     * @param: R
     * @param: lower
     * @param: upper
     * @updateTime 2022/11/16 16:13
     * @return: int -》原始的 nums 中有多少的累加和在 [lower, upper] 中
     * @throws
     * @description  求 sum 数组（前缀和数组）在 [L,...,R] 中
     */
    public static int count(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            // L = R 时，如果 sum[L] 在 [low, up] 区间内，即符合要求，
            // 说明 nums[0,...,L] 符合要求，且符合要求的数量为1
            return sum[L] <= upper && sum[L] >= lower ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        int leftPart = count(sum, L, mid, lower, upper);
        int rightPart = count(sum, mid + 1, R, lower, upper);
        int mergePart = merge(sum, L, mid, R, lower, upper);
        return leftPart + rightPart + mergePart;
    }

    public static int merge(long[] sum, int L, int mid, int R, int lower, int upper) {
        // 先不进行 merge
        // 首先求：对于右组中的每个数 x，求左组中有多少数，位于 [x - upper, x - lower] 中
        // 在遍历左组过程中，窗口是不回退的！所以还是 O(N) 的时间复杂度
        int ans = 0;
        int windowL = L;
        int windowR = L;
        for (int i = mid + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sum[windowL] < min) {
                windowL++;
            }
            // 当前左组的数有多少个达标
            ans += windowR - windowL;
        }
        // 然后才merge
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        for(int j = 0; j < help.length; j++) {
            sum[L + j] = help[j];
        }
        return ans;
    }
}
 
