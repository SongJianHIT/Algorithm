/**
 * @projectName Algorithm
 * @package algorithms.sort.merge_sort
 * @className algorithms.sort.merge_sort.BiggerThanRightTwice
 */
package algorithms.sort.merge_sort;

/**
 * BiggerThanRightTwice
 * @description 大于右边数两倍
 * @author SongJian
 * @date 2022/11/15 20:57
 * @version
 */
public class BiggerThanRightTwice {

    public int biggerThanRightTwice(int[] nums) {
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

    public static int merge (int[] arr, int l, int m, int r) {
        // [l...m] [m+1 .... r]
        // 先计数
        int ans = 0;
        int windowR = m + 1;
        for (int i = l; i <= m; i++) {
            // 遍历左组元素
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                // R指针向右推
                windowR++;
            }
            // 因为从 m + 1 开始
            ans += windowR - (m + 1);
        }

        // 剩下部分就是正常的 merge
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return ans;
    }
}
 
