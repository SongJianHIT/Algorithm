/**
 * @projectName Algorithm
 * @package data_structures.Unionfind_Sets
 * @className data_structures.Unionfind_Sets.NumberOfIsland2
 */

import java.util.ArrayList;
import java.util.List;

/**
 * NumberOfIsland2
 * @description
 * @author SongJian
 * @date 2022/12/12 11:08
 * @version
 */
public class NumberOfIsland2 {


    public static class UnionFind {
        private int[] parent;
        // 可以使用 size != 0 来标记该位置被初始化过
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        // 初始化
        public UnionFind(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = m * n;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + r;
        }

        /**
         * 连接方法，
         * @param r 行
         * @param c 列
         * @return
         */
        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                // 第一回来到这个位置才会执行
                // 多次到这个位置没有用，因为已经标记过是岛屿了
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

        /**
         * 找到代表节点
         * @param i
         * @return
         */
        public int find(int i) {
            int hi = 0;
            while (parent[i] != i) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        /**
         * 合并操作
         * @param r1
         * @param c1
         * @param r2
         * @param c2
         */
        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || c1 < 0 || c1 == col || r2 < 0 || r2 == row || c2 < 0 || c2 == col) {
                // 判断越界
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                // 只要有一个没有被初始化，说明有一个不是岛屿，不连接
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }

    /**
     * 主函数
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m ,n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }
}
 
