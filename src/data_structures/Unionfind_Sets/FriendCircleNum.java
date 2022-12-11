/**
 * @projectName Algorithm
 * @package data_structures.Unionfind_Sets
 * @className data_structures.Unionfind_Sets.FriendCircleNum
 */
package data_structures.Unionfind_Sets;

/**
 * FriendCircleNum
 * @description
 * @author SongJian
 * @date 2022/12/11 22:33
 * @version
 */
public class FriendCircleNum {

    public static int friendCircleNum(int[][] M) {
        int N = M.length;
        // 创建并查集，每个人构成一个单独的集合
        UnionFind unionFind = new UnionFind(N);
        // 只遍历对称数组的一半
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    // i 和 j 互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    /**
     * 数组实现并查集
     */
    public static class UnionFind{
        // parent[i] = k，则 i 的父亲是 k
        private int[] parent;
        // size[i] = k，则如果 i 是代表节点，size[i] 才有意义
        // i 所在集合的大小是多少
        private int[] size;
        // 辅助结构
        private int[] help;
        // 一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; ++i) {
                // 每个节点的父亲是自己
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * @title find
         * @author SongJian
         * @param: i
         * @updateTime 2022/12/11 22:50
         * @return: int
         * @throws
         * @description 从 i 开始，一直往上，往上到不能再往上，返回代表节点。同时要进行路径压缩
         */
        public int find(int i) {
            int hi = 0;
            // 找代表节点
            while (i != parent[i]) {
                // 记录沿途所有遇到的节点
                help[hi++] = i;
                // 一直往上找
                i = parent[i];
            }
            // 路径压缩
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            // 代表节点不同，才进行union
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
 
