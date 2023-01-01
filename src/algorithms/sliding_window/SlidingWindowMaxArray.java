/**
 * @projectName Algorithm
 * @package algorithms.sliding_window
 * @className algorithms.sliding_window.SlidingWindowMaxArray
 */
package algorithms.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * SlidingWindowMaxArray
 * @description
 * @author SongJian
 * @date 2023/1/1 10:41
 * @version
 */
public class SlidingWindowMaxArray {

    /**
     * ================================================================================================================
     * 暴力对数器方法
     * @title right
     * @author SongJian
     * @param: arr
     * @param: w
     * @updateTime 2023/1/1 10:42
     * @return: int[]
     * @throws
     * @description
     */
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        // 双端队列：窗口最大值的更新结构
        // 里面存储的是 arr 的元素下标
        Deque<Integer> qmax = new LinkedList<>();
        // 填 res 专用的
        int index = 0;
        for (int R = 0; R < arr.length; ++R) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - w) {
                // R - w 计算过期位置
                // 弹出过期位置
                qmax.pollFirst();
            }
            if (R >= w - 1) {
                // 最初还没形成窗口时，需要积累元素
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

