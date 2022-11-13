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
            for (int j = i - 1; j > 0 && arr[j + 1] < arr[j]; j--){
                // arr[j+1]需要与arr[j]交换
                swap(arr, j, j + 1);
            }
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1, 10, 5, 2, 6, 3, 2};
        System.out.println("排序前："+Arrays.toString(arr));
        insertSort(arr);
    }
}
 
