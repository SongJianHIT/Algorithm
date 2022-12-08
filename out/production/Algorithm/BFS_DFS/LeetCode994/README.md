# 994. 腐烂的橘子

## 题目描述

在给定的 `m x n` 网格 `grid` 中，每个单元格可以有以下三个值之一：

- 值 `0` 代表空单元格；
- 值 `1` 代表新鲜橘子；
- 值 `2` 代表腐烂的橘子。

每分钟，腐烂的橘子 **周围 4 个方向上相邻** 的新鲜橘子都会腐烂。

返回 *直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 `-1`* 。

**示例 1：**

**![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8fxsw663lj310a07n76k.jpg)**

```
输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
输出：4
```

**示例 2：**

```
输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
输出：-1
解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
```

**示例 3：**

```
输入：grid = [[0,2]]
输出：0
解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
```

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 10`
- `grid[i][j]` 仅为 `0`、`1` 或 `2`

## 思路与分析

有了 `LeetCode542` 的思路，本题就简单多了。`LeetCode542` 是一开始把所有的 0 放入队列中进行广度优先搜索，本题只需要变成 把所有的腐烂橘子 `grid[i][j] == 2` 放入队列中进行广度优先搜索即可。

此外，本题的要求是求最少需要多少时间，实际上就是求 **共需要 BFS 多少轮**，这是本题与 `LeetCode542` 的区别。 

怎么求解轮数呢？

**我们在 `while(!queue.isEmpty())` 中加一层 `for` 即可。这层 `for` 执行完成一次，就是所有的腐烂橘子向外扩展了一轮！** 这里的思路很常用，特别是在 **树的层序遍历** 上，用来判断共遍历了多少层，或者来对同一层的元素进行处理。

```java
class Solution {
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
```

- 时间复杂度： $O(nm)$ 即进行一次广度优先搜索的时间。


