/**
 * @projectName Algorithm
 * @package algorithms.sort.count_sort
 * @className algorithms.sort.count_sort.CountSort
 */
package algorithms.sort.count_sort;

/**
 * CountSort
 * @description 计数排序
 * @author SongJian
 * @date 2022/11/29 10:55
 * @version
 */
public class CountSort {
    // only for 0~200 value
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 数据区间大小
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 构建桶
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        // 重排即可
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
 
