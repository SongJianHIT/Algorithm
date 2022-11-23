/**
 * @projectName Algorithm
 * @package algorithms.sort.quick_sort
 * @className algorithms.sort.quick_sort.QuickSort
 */
package algorithms.sort.quick_sort;

import algorithms.sort.SortUtils;

/**
 * QuickSort
 * @description 快速排序三个版本
 * @author SongJian
 * @date 2022/11/23 12:06
 * @version
 */
public class QuickSort {

    /**
     * @title quickSort1
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/23 12:12
     * @throws
     * @description     快排 1.0 版本
     */

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                SortUtils.swap(arr, index, ++lessEqual);
            }
            index++;
        }
        SortUtils.swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // ======================================================================================

    /**
     * @title quickSort2
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/23 12:14
     * @throws
     * @description         快排 2.0 版本
     */

    public static void quickSort2(int arr[]) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L < R) {
            return new int[] {-1, -1};
        }
        if (L == R) {
            return new int[] {L, R};
        }
        // 小于区 与 大于区边界
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                SortUtils.swap(arr, ++less, index);
                index++;
            } else if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] > arr[R]) {
                // 注意！！这里只扩大于区间，不对index++
                SortUtils.swap(arr, --more, index);
            }
        }
        SortUtils.swap(arr, more, R);
        // 返回 等于区域的下标是从哪到哪的
        return new int[] {less + 1, more};
    }

    // =====================================================================


    public static void quickSort3(int arr[]) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }


    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        // 随机从数组中的一个数，放到最右边，作为划分值
        SortUtils.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }


}
 
