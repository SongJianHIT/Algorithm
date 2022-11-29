/**
 * @projectName Algorithm
 * @package algorithms.sort.radix_sort
 * @className algorithms.sort.radix_sort.RadixSort
 */
package algorithms.sort.radix_sort;

/**
 * RadixSort
 * @description
 * @author SongJian
 * @date 2022/11/29 11:07
 * @version
 */
public class RadixSort {

    /**
     * 基数排序入口，仅适用于非负数的排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * 获取数组中最大值，并返回最大值的位数
     * @param arr
     * @return 最大值的十进制位数
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    /**
     * 基数排序（不使用桶，优化版本）
     * @param arr
     * @param L
     * @param R
     * @param digit 最大值的十进制位数
     */
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        // 有多少位就进出几次
        for (int d = 1; d <= digit; d++) {
            // 10个空间，统计数组，不是桶
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是1的数字有多少个
            // count[2] 当前位(d位)是2的数字有多少个
            // count[i] 当前位(d位)是i的数字有多少个
            // count[0..9]
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                // 103  1   3
                // 209  1   9
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 进行累加和
            // count[0] 当前位(d位) 小于等于 0 的数字有多少个
            // count[1] 当前位(d位) 小于等于 1 即(0和1)的数字有多少个
            // count[2] 当前位(d位) 小于等于 2 即(0、1和2)的数字有多少个
            // count[i] 当前位(d位) 小于等于 i (0~i)的数字有多少个
            // count[0..9]
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 在辅助空间上直接放
            // 注意：是从右往左遍历
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 拷贝回 arr 数组
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    /**
     * 取出当前数 x 在 d 位上的数字
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

}
 
