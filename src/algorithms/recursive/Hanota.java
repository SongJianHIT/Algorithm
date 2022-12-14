/**
 * @projectName Algorithm
 * @package algorithms.recursive
 * @className algorithms.recursive.Hanota
 */
package algorithms.recursive;

/**
 * Hanota
 * @description 汉诺塔问题
 * @author SongJian
 * @date 2022/12/14 11:15
 * @version
 */
public class Hanota {
    /**
     *
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
     * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
     * 要求将所有盘子从第一根柱子移到最后一根柱子。
     *
     */


    /**
     * =================================================================================================
     *                                      方法：暴力递归，将问题拆解
     */
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 请把1~N层圆盘 从左 -> 右
    public static void leftToRight(int n) {
        if (n == 1) {
            // base case
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    // 请把1~N层圆盘 从左 -> 中
    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    /**
     * =================================================================================================
     *                                      优化：6个函数实际上都是 from - to - other 形式
     *                                      可抽象为一个函数实现
     */

    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }

    public static void func(int N, String from, String to, String other) {
        if (N == 1) {
            // base case
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(N - 1, from, other, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            func(N - 1, other, to, from);
        }
    }
    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
    }
}