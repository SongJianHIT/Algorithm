/**
 * @projectName Algorithm
 * @package data_structures.monotonous_stack
 * @className data_structures.monotonous_stack.MonotonousStack
 */
package data_structures.monotonous_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * MonotonousStack
 * @description 双侧较小元素
 * @author SongJian
 * @date 2023/1/2 10:24
 * @version
 */
public class MonotonousStack {

    /**
     * @title getNearLessNoRepeat
     * @author SongJian
     * @param: arr
     * @updateTime 2023/1/2 10:25
     * @return: int[][] 只存位置，第一列表示左边离他最近比他小的，第二列表示右边离它最近比他小的
     *          arr = [ 3, 1, 2, 3]
     *                  0  1  2  3
     *          返回：
     *       [
     *          0 : [-1,  1]
     *          1 : [-1, -1]
     *          2 : [ 1, -1]
     *          3 : [ 2, -1]
     *       ]
     * @throws
     * @description 需要保证 arr 中无重复值
     */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; ++i) {
            // 遍历到 arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                // 当前 arr[i] 落不到栈上的情况
                // 弹出栈顶元素，记录其返回值情况
                int popIndex = stack.pop();
                // 左侧最近比他小的在栈中
                // 右侧最近比他小的为 arr[i]
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            // 左侧最近比他小的在栈中
            // 右侧最近比他小的为 没有
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * @title getNearLess
     * @author SongJian
     * @param: arr
     * @updateTime 2023/1/2 10:36
     * @return: int[][]
     * @throws
     * @description arr 中可存在重复值的版本
     */
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        // 栈中存放的是链表
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; ++i) {
            // i -> arr[i] 进栈
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                // 弹出栈顶链表，链表中的每个位置结算答案
                List<Integer> popIs = stack.pop();
                // 左侧最近比他小的在栈顶链表中最后一个元素
                // 右侧最近比他小的为 arr[i]
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            // arr[i] 与栈顶元素相等
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                // 不相等，则自己创一个链表
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIds = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIds) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

