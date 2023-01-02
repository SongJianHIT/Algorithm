/**
 * @projectName Algorithm
 * @package data_structures.monotonous_stack
 * @className data_structures.monotonous_stack.AllTimesMinToMax
 */
package data_structures.monotonous_stack;

import java.util.Stack;

/**
 * AllTimesMinToMax
 * @description 子数组的最大指标
 * @author SongJian
 * @date 2023/1/2 11:07
 * @version
 */
public class AllTimesMinToMax {

    /**
     * @title max1
     * @author SongJian
     * @param: arr
     * @updateTime 2023/1/2 11:08
     * @return: int
     * @throws
     * @description 暴力方法 O(n^3)
     */
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    /**
     * @title max2
     * @author SongJian
     * @param: arr
     * @updateTime 2023/1/2 11:09
     * @return: int
     * @throws
     * @description 使用单调栈和前缀和解决
     */
    public static int max2(int[] arr) {
        int size = arr.length;
        // 前缀和数组
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; ++i) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                // 以 arr[popIndex] 为最小值的子数组最大指标
                int popIndex = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[popIndex]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[popIndex]);
        }
        return max;
    }
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}

