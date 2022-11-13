/**
 * @projectName Algorithm
 * @package sort
 * @className sort.InsertSort
 */
package sort;

import java.util.Arrays;

/**
 * InsertSort
 * @description 插入排序
 * @author SongJian
 * @date 2022/11/13 11:29
 * @version
 */
public class InsertSort {
    /**
     * @title insertSort
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 11:31
     * @throws
     * @description 插入排序，时间复杂度：O(n^2)
     */
    public static void insertSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 0 ~ 0 有序
        // 0 ~ i 想有序
        for (int i = 0; i < arr.length; i++){
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--){
                // arr[j+1]需要与arr[j]交换
                SortUtils.swap(arr, j, j + 1);
            }
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
            insertSort(arr1);
            SortUtils.comparator(arr2);
            if(!SortUtils.isEqual(arr1, arr2)){
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
        int[] arr = SortUtils.generateRandomArray(maxSize, maxValue);
        SortUtils.printArray(arr);
        insertSort(arr);
        SortUtils.printArray(arr);
    }
}
 
