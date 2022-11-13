/**
 * @projectName Algorithm
 * @package sort
 * @className sort.SelectionSort
 */
package sort;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * SelectionSort
 * @description 选择排序
 * @author SongJian
 * @date 2022/11/13 10:58
 * @version
 */
public class SelectionSort {
    /**
     * @title selectionSort
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 11:11
     * @throws
     * @description 选择排序，时间复杂度：O(n^2)
     */
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 0 ～ N-1 找到最小值在哪，放到 0 位置
        // 1 ～ N-1 找到最小值在哪，放到 1 位置
        // ....
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 交换
            swap(arr, i, minIndex);
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void swap(int [] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1, 10, 5, 2, 6, 3, 2};
        System.out.println("排序前："+Arrays.toString(arr));
        selectionSort(arr);
    }
}
 
