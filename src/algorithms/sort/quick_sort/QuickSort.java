/**
 * @projectName Algorithm
 * @package algorithms.sort.quick_sort
 * @className algorithms.sort.quick_sort.QuickSort
 */
package algorithms.sort.quick_sort;

import algorithms.sort.SortUtils;

import java.util.Stack;

/**
 * QuickSort
 * @description 快速排序三个版本
 * @author SongJian
 * @date 2022/11/23 12:06
 * @version
 */
public class QuickSort {

    /**
     * @title quickSort1
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/23 12:12
     * @throws
     * @description     快排 1.0 版本
     */

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                SortUtils.swap(arr, index, ++lessEqual);
            }
            index++;
        }
        SortUtils.swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // ======================================================================================

    /**
     * @title quickSort2
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/23 12:14
     * @throws
     * @description         快排 2.0 版本
     */

    public static void quickSort2(int arr[]) {
        if (arr == null || arr.length < 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] {-1, -1};
        }
        if (L == R) {
            return new int[] {L, R};
        }
        // 小于区 与 大于区边界
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                SortUtils.swap(arr, index++, ++less);
            } else { // >
                SortUtils.swap(arr, index, --more);
            }
        }
        SortUtils.swap(arr, more, R);
        // 返回 等于区域的下标是从哪到哪的
        return new int[] {less + 1, more};
    }

    // ==================================================================================

    /**
     * @title quickSort3
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/23 12:30
     * @throws
     * @description     快排 3.0 ：随机快排
     */

    public static void quickSort3(int arr[]) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }


    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        // 随机从数组中的一个数，放到最右边，作为划分值
        SortUtils.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    // =======================================================================================

    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    // 快排3.0 非递归版本 用栈来执行
    public static void quickSort_stack(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        SortUtils.swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l ... op.r
            if (op.l < op.r) {
                SortUtils.swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }





    // =======================================================================================

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
 
