/**
 * @projectName Algorithm
 * @package algorithms.sort.merge_sort
 * @className algorithms.sort.merge_sort.mergeSort
 */
package algorithms.sort.merge_sort;

/**
 * mergeSort
 * @description 归并排序
 * @author SongJian
 * @date 2022/11/15 15:52
 * @version
 */
public class MergeSort {

    /**
     * @title mergeSort_1
     * @author SongJian
     * @updateTime 2022/11/15 16:17
     * @throws
     * @description 递归版归并排序
     */
    public static void mergeSort_recur(int[] arr) {
        if (arr == null || arr.length < 2)  return;
        process(arr, 0, arr.length - 1);
    }

    /**
     * @title process
     * @author SongJian
     * @param: arr
     * @param: L
     * @param: R
     * @updateTime 2022/11/15 16:19
     * @throws
     * @description 辅助方法，将 arr[L, R] 排有序
     */
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /**
     * @title merge
     * @author SongJian
     * @param: arr
     * @param: L
     * @param: mid
     * @param: R
     * @updateTime 2022/11/15 16:22
     * @throws
     * @description 合并两边有序结果。此时 arr[L, mid] 有序，arr[mid+1, R] 也有序
     */
    public static void merge(int[] arr, int L, int mid, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        // 辅助数组指针
        int i = 0;
        // 第一个有序数组的起始位置
        int p1 = L;
        // 第二个有序数组的起始位置
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            // 两个指针都没越界
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while(p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 将辅助数组 help 拷贝回 arr
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    /**
     * @title mergeSort_non_recur
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/15 16:38
     * @throws
     * @description 归并排序的非递归方法实现
     */
    public static void mergeSort_non_recur(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // mergeSize 为步长（可理解为半径）
        int mergeSize = 1;
        while (mergeSize < N) {     //logN
            // L：当前左组的第一个位置
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) break;
                // 如果右边界越界，则拿数组右边界为 R
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            /**
             *  防止溢出！！
             *  如果数组长度很接近 Integer.MAX_VALUE，
             *  他在 N/2 时还没溢出，但乘 2 之后可能会溢出
             *  溢出之后就变成负数，仍会满足循环条件！！！
             */
            if (mergeSize > N / 2) break;
            mergeSize <<= 1;
        }
    }

}
 
