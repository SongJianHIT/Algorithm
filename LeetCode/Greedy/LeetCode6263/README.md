# 6263. 青蛙过河 II

> 第93场双周赛的第三题，没做出来...

## 题目描述

给你一个下标从 **0** 开始的整数数组 `stones` ，数组中的元素 **严格递增** ，表示一条河中石头的位置。

一只青蛙一开始在第一块石头上，它想到达最后一块石头，然后回到第一块石头。同时每块石头 **至多** 到达 **一次。**

一次跳跃的 **长度** 是青蛙跳跃前和跳跃后所在两块石头之间的距离。

- 更正式的，如果青蛙从 `stones[i]` 跳到 `stones[j]` ，跳跃的长度为 `|stones[i] - stones[j]|` 。

一条路径的 **代价** 是这条路径里的 **最大跳跃长度** 。

请你返回这只青蛙的 **最小代价** 。

**示例 1：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8zlaej5scj30q508xaau.jpg)

```
输入：stones = [0,2,5,6,7]
输出：5
解释：上图展示了一条最优路径。
这条路径的代价是 5 ，是这条路径中的最大跳跃长度。
无法得到一条代价小于 5 的路径，我们返回 5 。
```

**示例 2：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8zlaft1cqj30qp09rq46.jpg)

```
输入：stones = [0,3,9]
输出：9
解释：
青蛙可以直接跳到最后一块石头，然后跳回第一块石头。
在这条路径中，每次跳跃长度都是 9 。所以路径代价是 max(9, 9) = 9 。
这是可行路径中的最小代价。
```

**提示：**

- `2 <= stones.length <= 105`
- `0 <= stones[i] <= 109`
- `stones[0] == 0`
- `stones` 中的元素严格递增。

## 解法：贪心

本题的贪心真没反应过来，被青蛙来回跳这个过程给绕晕了。

实际上，可以将问题进行转换！

- **可以把一只青蛙来回往返的过程，可以看成两只青蛙同时从起点出发，不重复踩石头过河！**

接下来，怎么跳能够代价最小呢？

- **间隔跳！这就是贪心的想法，每一只青蛙都间隔跳，保证每次跳都不会过长过短。**

下图展示了这一思路，其中去程和回程可互换：

<img src="https://tva1.sinaimg.cn/large/008vxvgGgy1h8zlfxe81oj30zn0rrmz9.jpg" alt="frog-jump-ii.png" style="zoom:50%;" />

## 代码

```java
class Solution {
    public int maxJump(int[] stones) {
        int len = stones.length;
        int ans = stones[1] - stones[0];
        for (int i = 2; i < len; ++i) {
            ans = Math.max(ans, stones[i] - stones[i - 2]);
        }
        return ans;
    }
}
```

时间复杂度： $O(N)$

空间复杂度： $O(1)$

