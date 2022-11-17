/**
 * @projectName Algorithm
 * @package Array
 * @className Array.Rotate
 */
package Array;

/**
 * Rotate
 * @description 189. 轮转数组
 * @author SongJian
 * @date 2022/11/17 10:36
 * @version
 */
public class Rotate {
    // 方法一：额外辅助数组
    public void rotate1(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < 2) return;
        // 若k大于数组长度，要进行求余！
        k %= nums.length;
        int[] help = new int[nums.length];
        int i = 0;
        for (; i < nums.length - k; i++) {
            help[i + k] = nums[i];
        }
        for (int j = 0; j < k; j++) {
            help[j] = nums[j + i];
        }
        for (i = 0; i < nums.length; i++) {
            nums[i] = help[i];
        }
    }
    // 方法二：旋转数组
    public void rotate(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < 2) return;
        // 若k大于数组长度，要进行求余！
        k %= nums.length;
        // 数组翻转！！！
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        if (end < start) return;
        int tmp = nums[start];
        while (start < end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
 
