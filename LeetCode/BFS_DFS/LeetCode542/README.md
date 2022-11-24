# 542. 01 矩阵

## 题目描述

给定一个由 `0` 和 `1` 组成的矩阵 `mat` ，请输出一个大小相同的矩阵，其中每一个格子是 `mat` 中对应位置元素到最近的 `0` 的距离。

两个相邻元素间的距离为 `1` 。 

**示例 1：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8fw87ae45j3071071747.jpg)

```
输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
输出：[[0,0,0],[0,1,0],[0,0,0]]
```

**示例 2：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8fw85htqej3071071q2u.jpg)

```
输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]
```

**提示：**

- `m == mat.length`
- `n == mat[i].length`
- `1 <= m, n <= 104`
- `1 <= m * n <= 104`
- `mat[i][j] is either 0 or 1.`
- `mat` 中至少有一个 `0 `

## 分析与思路

假设只有一个零，我们会怎么做呢？

由于矩阵中只有一个 0，那么对于每一个 1，离它最近的 0 就是那个唯一的 0。如何求出这个距离呢？

我们可以从 0 的位置开始进行 **广度优先搜索**。广度优先搜索可以 **找到从起点到其余所有点的最短距离**，因此如果我们从 0 开始搜索，每次搜索到一个 1，就可以得到 0 到这个 1 的最短距离，也就离这个 1 最近的 0 的距离了（因为矩阵中只有一个 0）。

那么对于多个零，我们怎么进行广度优先搜索呢？

**只需要在一开始把全部 0 放入队列中即可**！

```java
class Solution {
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
```

- 时间复杂度： $O(mn)$ ，其中 m 为矩阵行数，n 为矩阵列数，即矩阵元素个数。**广度优先搜索中每个位置最多只会被加入队列一次**，因此只需要 $O(mn)$ 的时间复杂度。

- 空间复杂度： $O(mn)$ ，其中 m 为矩阵行数，n 为矩阵列数，即矩阵元素个数。除答案数组外，最坏情况下矩阵里所有元素都为 0，全部被加入队列中，此时需要 $O(mn)$ 的空间复杂度。













