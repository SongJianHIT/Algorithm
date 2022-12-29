/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.MinPathSum
 */
package algorithms.dynamic_programming;

/**
 * MinPathSum
 * @description 最小路径累加和
 * @author SongJian
 * @date 2022/12/29 10:27
 * @version
 */
public class MinPathSum {

    /**
     * @title minPathSum1
     * @author SongJian
     * @param: m
     * @updateTime 2022/12/29 10:40
     * @return: int
     * @throws
     * @description dp解
     */
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        // dp[i][j] 表示从 [0][0] 出发，到达 [i][j] 位置的最短路径和
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * @title minPathSum2
     * @author SongJian
     * @param: m
     * @updateTime 2022/12/29 10:41
     * @return: int
     * @throws
     * @description 表压缩优化，节省空间
     */
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        // dp 数组，每一行的解
        int[] dp = new int[col];
        dp[0] = m[0][0];
        // 填好第 0 行的值
        for (int j = 1; j < col; ++j) {
            dp[j] = m[0][j] + dp[j - 1];
        }
        for (int i = 1; i < row; ++i) {
            // 先好填每一行的第一个
            dp[0] = dp[0] + m[i][0];
            // 再填其他的
            for (int j = 1; j < col; ++j) {
                // dp[j - 1]：左侧
                // dp[j]：上侧
                dp[j] = m[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[col - 1];
    }


    /**
     * ==============================================================================================================
     * 测试
     */
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));

    }
}

