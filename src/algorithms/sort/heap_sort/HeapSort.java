/**
 * @projectName Algorithm
 * @package algorithms.sort.heap_sort
 * @className algorithms.sort.heap_sort.HeapSort
 */
package algorithms.sort.heap_sort;

import algorithms.sort.SortUtils;

/**
 * HeapSort
 *
 * @author SongJian
 * @description 堆排序
 * @date 2022/11/25 09:56
 */
public class HeapSort {

    public static Heap heap = new Heap();

    public static void heapSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N*logN)
        for (int i = 0; i < arr.length; i++) {
            // O(logN)
            heap.heapInsert(arr, i);
        }
        int heapSize = arr.length;

        // 把全局最大值放到堆末尾，然后断连
        SortUtils.swap(arr, 0, --heapSize);

        // O(N*logN)
        while (heapSize > 0) {
            // O(logN)
            heap.heapify(arr, 0, heapSize);
            SortUtils.swap(arr, 0, --heapSize);
        }
    }

    /**
     * @title heapSort_2
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/25 10:41
     * @throws
     * @description 不使用 heapInsert 方法实现的推排序
     */

    public static void heapSort_2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heap.heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        SortUtils.swap(arr, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) {
            heap.heapify(arr, 0, heapSize);
            SortUtils.swap(arr, 0, --heapSize);
        }
    }

}
 
