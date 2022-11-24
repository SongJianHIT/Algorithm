/**
 * @projectName Algorithm
 * @package BFS_DFS.LeetCode542
 * @className BFS_DFS.LeetCode542.UpdateMatrix
 */
package BFS_DFS.LeetCode542;

import java.util.LinkedList;
import java.util.Queue;

/**
 * UpdateMatrix
 * @description 542. 01 矩阵
 * @author SongJian
 * @date 2022/11/24 08:13
 * @version
 */
public class UpdateMatrix {
    static int[][] move = {{-1, 0}, {1, 0},  {0, 1}, {0, -1}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] distance = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // 先遍历一次，把所有0添加到初始队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    visit[i][j] = true;
                }
            }
        }
        // 从0出发，进行广度优先遍历
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0], y = tmp[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + move[i][0];
                int my = y + move[i][1];
                if (mx >= 0 && mx < m && my >= 0 && my < n && !visit[mx][my]) {
                    queue.offer(new int[]{mx, my});
                    distance[mx][my] = distance[x][y] + 1;
                    visit[mx][my] = true;
                }
            }
        }
        return distance;
    }
}
 
