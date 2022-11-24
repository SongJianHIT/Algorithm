/**
 * @projectName Algorithm
 * @package sort
 * @className sort.SelectionSort
 */
package algorithms.sort.selection_sort;

import algorithms.sort.SortUtils;

/**
 * SelectionSort
 *
 * @author SongJian
 * @description 选择排序
 * @date 2022/11/13 10:58
 */
public class SelectionSort {

    /**
     * @throws
     * @title selectionSort
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 11:11
     * @description 选择排序，时间复杂度：O(n^2)
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ～ N-1 找到最小值在哪，放到 0 位置
        // 1 ～ N-1 找到最小值在哪，放到 1 位置
        // ....
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 交换
            SortUtils.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortUtils.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortUtils.copyArray(arr1);
            selectionSort(arr1);
            SortUtils.comparator(arr2);
            if (!SortUtils.isEqual(arr1, arr2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
        int[] arr = SortUtils.generateRandomArray(maxSize, maxValue);
        SortUtils.printArray(arr);
        selectionSort(arr);
        SortUtils.printArray(arr);
    }
}
 
