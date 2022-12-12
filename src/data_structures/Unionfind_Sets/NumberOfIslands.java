/**
 * @projectName Algorithm
 * @package data_structures.Unionfind_Sets
 * @className data_structures.Unionfind_Sets.NumberOfIslands
 */
package data_structures.Unionfind_Sets;

/**
 * NumberOfIslands
 * @description
 * @author SongJian
 * @date 2022/12/12 10:12
 * @version
 */
public class NumberOfIslands {

    /**
     * @title numsIslands3
     * @author SongJian
     * @param: board
     * @updateTime 2022/12/12 10:15
     * @return: int
     * @throws
     * @description 方法三主方法
     */
    public static int numsIslands1(char[][] board) {
        int islands = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '1') {
                    islands++;
                    // 把遍历过的岛修改成2
                    infect(board, i, j);
                }
            }
        }
        return islands;
    }

    /**
     * 感染过程
     * @param board
     * @param i
     * @param j
     */
    public static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            // 越界 或者 不为1 就不进行感染过程
            return;
        }
        board[i][j] = 2;
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }


    public static int numsIslands2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind uf = new UnionFind(board);
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.sets();
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        // 列数
        private int col;
        private int sets;

        public UnionFind(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = col * row;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        // 矩阵对应到向量
        private int index(int r, int c) {
            return r * col + c;
        }

        /**
         * 找代表节点
         * @param i 下标值
         * @return
         */
        private int find(int i) {
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
         * (r1, c1) 与 (r2, c2) 进行合并
         * @param r1
         * @param c1
         * @param r2
         * @param c2
         * @return
         */
        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
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

        public int sets() {
            return sets;
        }
    }
}
 
