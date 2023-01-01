/**
 * @projectName Algorithm
 * @package algorithms.sliding_window
 * @className algorithms.sliding_window.AllLessNumSubArray
 */
package algorithms.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * AllLessNumSubArray
 * @description 达标子数组
 * @author SongJian
 * @date 2023/1/1 11:00
 * @version
 */
public class AllLessNumSubArray {

    /**
     * 暴力对数器
     * @title right
     * @author SongJian
     * @param: arr
     * @param: sum
     * @updateTime 2023/1/1 11:00
     * @return: int
     * @throws
     * @description O(N^3)
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @title num
     * @author SongJian
     * @param: arr
     * @param: sum
     * @updateTime 2023/1/1 11:02
     * @return: int
     * @throws
     * @description 使用窗口内最大最小值的更新结构
     */
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        Deque<Integer> maxWindow = new LinkedList<>();
        Deque<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; ++L) {
            // 尝试 [L....R)
            // L == R，窗口没数
            while (R < N) {
                // 扩到初次不达标的时候停
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    // 不合法，不能扩了
                    break;
                } else {
                    R++;
                }
            }
            count += R - L;
            // L过期，则弹出
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}

