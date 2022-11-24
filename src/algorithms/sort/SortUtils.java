/**
 * @projectName Algorithm
 * @package sort
 * @className sort.SortUtils
 */
package algorithms.sort;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * SortUtils
 *
 * @author SongJian
 * @description 排序算法的工具类：测试对数器等方法
 * @date 2022/11/13 15:32
 */
public class SortUtils {


    private SortUtils() {
    }

    /**
     * @throws
     * @title swap
     * @author SongJian
     * @param: arr
     * @param: i
     * @param: j
     * @updateTime 2022/11/13 15:35
     * @description 两个元素交换
     */

    public static void swap(int[] arr, int i, int j) {

        // arr[i] = arr[i] ^ arr[j];
        // arr[j] = arr[i] ^ arr[j];
        // arr[i] = arr[i] ^ arr[j];
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * @throws
     * @title comparator
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 15:15
     * @description 对比方法
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * @throws
     * @title generateRandomArray
     * @author SongJian
     * @param: maxSize
     * @param: maxValue
     * @updateTime 2022/11/13 15:14
     * @return: int[]
     * @description 随机生成
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0, 1) 所有小数，等概率返回一个
        // Math.random() * N-> [0, N) 所有小数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) + Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * @throws
     * @title copyArray
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 15:23
     * @return: int[]
     * @description 拷贝数组
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * @throws
     * @title isEqual
     * @author SongJian
     * @param: arr1
     * @param: arr2
     * @updateTime 2022/11/13 15:26
     * @return: boolean
     * @description 判断两个数组是否相等
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @throws
     * @title printArray
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 15:29
     * @description 打印数组
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
 
