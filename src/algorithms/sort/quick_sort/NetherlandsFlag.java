/**
 * @projectName Algorithm
 * @package algorithms.sort.quick_sort
 * @className algorithms.sort.quick_sort.NetherlandsFlag
 */
package algorithms.sort.quick_sort;

import algorithms.sort.SortUtils;

/**
 * NetherlandsFlag
 * @description 荷兰国旗问题
 * @author SongJian
 * @date 2022/11/23 11:22
 * @version
 */
public class NetherlandsFlag {
    /**
     * arr[L..R]，以 arr[R] 作为划分值
     * <arr[R]  ==arr[R]  >arr[R]
     * @param arr
     * @param L
     * @param R
     * @return 返回等于区域的下标是从哪到哪的
     */
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

    /**
     * arr[L..R]
     * 划分为 <= arr[R]  >arr[R]
     * 在 <=arr[R] 最后一个元素为 arr[R]
     * @param arr
     * @param L
     * @param R
     * @return 返回小于等于区的指针位置
     */
    public static int partiation(int[] arr, int L, int R) {
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
                SortUtils.swap(arr, ++lessEqual, index);
            }
            index++;
        }
        // 最后交换 arr[R] 与小于等于区下一个元素
        // 因为这里把 arr[R] 当作 target
        SortUtils.swap(arr, ++lessEqual, R);
        return lessEqual;
    }
}
 
