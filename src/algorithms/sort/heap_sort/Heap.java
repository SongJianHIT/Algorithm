/**
 * @projectName Algorithm
 * @package algorithms.sort.heap_sort
 * @className algorithms.sort.heap_sort.Heap堆
 */
package algorithms.sort.heap_sort;

import algorithms.sort.SortUtils;

import java.io.*;
import java.util.PriorityQueue;

/**
 * Heap堆
 *
 * @author SongJian
 * @description 大根堆
 * @date 2022/11/24 11:30
 */
public class Heap {
    /**
     * @throws
     * @title heapInsert
     * @author SongJian
     * @param: arr
     * @param: index
     * @updateTime 2022/11/24 11:31
     * @description 向堆中插入元素 arr[index]
     */
    private void heapInsert(int[] arr, int index) {
        // arr[index] 不比 arr[index父] 大了，停止
        while (arr[index] > arr[(index - 1) / 2]) {
            SortUtils.swap(arr, index, ((index - 1) / 2));
            index = (index - 1) / 2;
        }
    }

    /**
     * @throws
     * @title heapify
     * @author SongJian
     * @param: arr
     * @param: index
     * @param: heapSize
     * @updateTime 2022/11/24 11:51
     * @description 从 index 的位置，往下看，不断地下沉
     */
    private void heapify(int arr[], int index, int heapSize) {
        // 左孩子
        int left = index * 2 + 1;
        // 停：较大的孩子都不再比我大，或者已经没有孩子了
        while (left <= heapSize) {
            // 左右孩子中的最大值，下标
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 比较父子节点值，下标
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                // 父结点比孩子节点都大，不需要继续下沉了
                break;
            }
            // 交换父子节点
            SortUtils.swap(arr, largest, index);
            // 下沉
            index = largest;
            // 继续考查孩子节点
            left = index * 2 + 1;
        }
    }
    BufferedReader

}
 
