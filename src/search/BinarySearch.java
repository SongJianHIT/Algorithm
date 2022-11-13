/**
 * @projectName Algorithm
 * @package search
 * @className search.BinarySearch
 */
package search;

import org.junit.jupiter.api.Test;

/**
 * BinarySearch
 * @description 二分搜索算法
 * @author SongJian
 * @date 2022/11/13 16:12
 * @version
 */
public class BinarySearch {
    /**
     * @title binarySearch
     * @author SongJian
     * @param: sortedArr
     * @param: num
     * @updateTime 2022/11/13 16:17
     * @throws
     * @description 在 有序数组 中，判断某个元素是否存在
     */
    public static boolean binarySearch(int[] sortedArr, int num){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L <= R){
            // mid = (L + R) / 2 is not safe!
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num){
                return true;
            } else if (sortedArr[mid] > num){
                // 说明此时中点的元素往后都比 num 大
                // 移动区间右端点 R
                R = mid - 1;
            } else {
                // 同理
                L = mid + 1;
            }
        }
        return false;
    }

    /**
     * @title nearestIndex
     * @author SongJian
     * @param: arr
     * @param: value
     * @updateTime 2022/11/13 16:43
     * @return: int
     * @throws
     * @description 在数组 arr 中，找到满足 >= value 的最左侧位置
     */
    public static int nearestIndex(int[] arr, int value){
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1; // 记录最左的索引
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else{
                L = mid + 1;
            }
        }
        return index;
    }

    /**
     * @title getLocalMiniElement
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 17:29
     * @return: int
     * @throws
     * @description 局部最小值问题求解代码
     */
    public static int getLocalMiniElementIndex(int[] arr){
        if (arr == null || arr.length == 0) {
            // 不存在
            return -1;
        }
        if (arr[0] < arr[1]) {
            // 比较数组开头
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            // 比较数组末尾
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while(L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            }
        }
        return L;
    }
    @Test
    public void test() {
        int[] arr = new int[] {5,0,3,8,5,6,7};
        System.out.println(getLocalMiniElementIndex(arr));
    }
}
 
