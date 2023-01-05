/**
 * @projectName Algorithm
 * @package algorithms.bfprt
 * @className algorithms.bfprt.FindMinKth
 */
package algorithms.bfprt;

import java.util.PriorityQueue;

/**
 * FindMinKth
 * @description 无序数组中第K小的数
 * @author SongJian
 * @date 2023/1/5 21:57
 * @version
 */
public class FindMinKth {

    /**
     * ================================================================================================================
     * @title minKth1
     * @author SongJian
     * @param: array
     * @param: k
     * @updateTime 2023/1/5 22:16
     * @return: int
     * @throws
     * @description 大根堆，时间复杂度为 O(N*logK)
     */
    public static int minKth1(int[] array, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
        for (int i = 0; i < k; ++i) {
            maxHeap.add(array[i]);
        }
        for (int i = k; i < array.length; ++i) {
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(array[i]);
            }
        }
        return maxHeap.peek();
    }



    /**
     * ================================================================================================================
     * @title minKth2
     * @author SongJian
     * @param: array
     * @param: k , k >= 1
     * @updateTime 2023/1/5 21:57
     * @return: int
     * @throws
     * @description 改进的快排，时间复杂度为 O(N)
     */
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * arr[L, R] 范围上，如果排完序的话，找位于 index 的数
     * 第 k 小，就是 index = k - 1
     * @param arr
     * @param L
     * @param R
     * @param index
     * @return
     */
    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // 随机选择一个数 L + [0, R - L]
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];

        // range : 等于区域的 「左边界」 和 「右边界」
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            // 命中等于区域
            return arr[index];
        } else if (index < range[0]) {
            // 命中小于区域
            return process2(arr, 0, range[0] - 1, index);
        } else {
            // 命中大于区域
            return process2(arr, range[1] + 1, R, index);
        }
    }

    /**
     * 荷兰国旗问题，partition
     * @param arr
     * @param L
     * @param R
     * @param pivot
     * @return
     */
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    /**
     * ================================================================================================================
     * @title minKth3
     * @author SongJian
     * @param: array
     * @param: k
     * @updateTime 2023/1/5 22:33
     * @return: int
     * @throws
     * @description bfprt 算法
     */
    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
     * 与快排中的 partiton 算法不同的是，这里的 pivot 的选择并不是随机的！
     * @param arr
     * @param L
     * @param R
     * @param index
     * @return
     */
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // 有选择性地选取 pivot
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    /**
     * arr[L...R]  五个数一组
     * 每个小组内部排序
     * 每个小组中位数领出来，组成 marr
     * marr 中的中位数，返回
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; ++team) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        // marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

