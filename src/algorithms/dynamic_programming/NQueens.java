/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.NQueens
 */
package algorithms.dynamic_programming;

/**
 * NQueens
 * @description N皇后问题
 * @author SongJian
 * @date 2022/12/31 11:32
 * @version
 */
public class NQueens {

    /**
     * ================================================================================================================
     * 暴力递归法
     * @title num1
     * @author SongJian
     * @param: n
     * @updateTime 2022/12/31 11:49
     * @return: int
     * @throws
     * @description
     */

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     * 当前来到 i 行，一共是 0～N-1 行
     * 在 i 行上放皇后，所有列都尝试，必须要保证跟之前所有的皇后不打架
     * int[] record，record[x] = y 之前的第 x 行的皇后，放在了 y 列上
     * 返回：不关心 i 以上发生了什么，i.... 后续有多少合法的方法数
     * @param i
     * @param record
     * @param n
     * @return
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 做尝试
        for (int j = 0; j < n; ++j) {
            if (isVaild(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    /**
     * 判断皇后放在 [i][j] 坐标会不会冲突
     * @param record
     * @param i
     * @param j
     * @return
     */
    public static boolean isVaild(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            // 列不冲突、斜线不冲突
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }



    /**
     * ================================================================================================================
     * 使用 二进制 改进
     * @title num2
     * @author SongJian
     * @param: n
     * @updateTime 2022/12/31 11:50
     * @return: int
     * @throws
     * @description
     */

    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    /**
     * ================================================================================================================
     * 测试
     */

    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}

