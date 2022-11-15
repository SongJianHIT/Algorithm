/**
 * @projectName Algorithm
 * @package algorithms.sort.merge_sort
 * @className algorithms.sort.merge_sort.SmallSum
 */
package algorithms.sort.merge_sort;

/**
 * SmallSum
 * @description 小和问题
 * @author SongJian
 * @date 2022/11/15 17:45
 * @version
 */
public class SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    /**
     * @title process
     * @author SongJian
     * @param: arr
     * @param: L
     * @param: R
     * @updateTime 2022/11/15 17:49
     * @return: int
     * @throws
     * @description   arr[L..R] 既要排好序，又要求返回小和
     *                最终小和数 = 左组产生的小和总量 + 右组产生的小和总量 + 汇总时产生的小和总量
     */

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R -L) >> 1);
        return process(arr, 1, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    /**
     * @title merge
     * @author SongJian
     * @param: arr
     * @param: L
     * @param: M
     * @param: R
     * @updateTime 2022/11/15 17:51
     * @return: int
     * @throws
     * @description merge 中产生小和
     */
    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            // 如果左组元素小，产生小和
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return res;
    }
}
 
