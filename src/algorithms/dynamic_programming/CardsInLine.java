/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.CardsInLine
 */
package algorithms.dynamic_programming;

/**
 * CardsInLine
 * @description 拿纸牌问题
 * @author SongJian
 * @date 2022/12/19 09:45
 * @version
 */
public class CardsInLine {

    /**
     * =============================================================================================================
     * 暴力递归，自然智慧
     * @title win1
     * @author SongJian
     * @param: arr
     * @updateTime 2022/12/19 09:48
     * @return: int
     * @throws
     * @description 在数组 arr 上，返回赢家的分数
     */
    public static int win1 (int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    /**
     * 先手玩家，在 arr[L...R] 上能获得的最大得分是多少
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int f1 (int[] arr, int L, int R) {
        if (L == R) {
            // base case：只剩一张牌
            return arr[L];
        }
        // 两种取牌方法
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    /**
     * 后手玩家，在 arr[L...R] 上能够获得的最大得分
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int g1 (int[] arr, int L, int R) {
        if (L == R) {
            // base case：只剩一张牌，后手无法拿到牌了
            return 0;
        }
        int p1 = f1(arr, L + 1, R);
        int p2 = f1(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    /**
     * =============================================================================================================
     * 傻缓存法
     * @title win2
     * @author SongJian
     * @param: arr
     * @updateTime 2022/12/19 10:23
     * @return: int
     * @throws
     * @description
     */
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }
    public static int f2 (int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            // base case：只剩一张牌
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }
    public static int g2 (int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap);
            int p2 = f2(arr, L, R - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    /**
     * =============================================================================================================
     * 动态规划：严格表依赖
     * @title win3
     * @author SongJian
     * @param: arr
     * @updateTime 2022/12/19 10:34
     * @return: int
     * @throws
     * @description
     */
    public static int win3 (int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; ++i) {
            fmap[i][i] = arr[i];
            gmap[i][i] = 0;
        }
        for (int startCol = 1; startCol < N; ++startCol) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    /**
     *=============================================================================================================
     * 测试
     */
    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
 
