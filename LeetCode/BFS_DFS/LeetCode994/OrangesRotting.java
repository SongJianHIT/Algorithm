/**
 * @projectName Algorithm
 * @package BFS_DFS.LeetCode994
 * @className BFS_DFS.LeetCode994.OrangesRotting
 */
package BFS_DFS.LeetCode994;

import java.util.LinkedList;
import java.util.Queue;

/**
 * OrangesRotting
 * @description 994. 腐烂的橘子
 * @author SongJian
 * @date 2022/11/24 09:10
 * @version
 */
public class OrangesRotting {
    static int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;
        int around = 0;
        // 把腐烂橘子入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        // 广度优先搜索
        while (count > 0 && !queue.isEmpty()) {
            around++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] tmp = queue.poll();
                int x = tmp[0], y = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int mx = x + move[i][0];
                    int my = y + move[i][1];
                    if(mx >= 0 && mx < m && my >= 0 && my < n && grid[mx][my] == 1) {
                        // 不使用 visit 的话，需要修改为腐烂橘子，防止重复搜索
                        grid[mx][my] = 2;
                        queue.offer(new int[]{mx, my});
                        count--;
                    }
                }
            }

        }
        return count > 0 ? -1 : around;
    }
}
 
