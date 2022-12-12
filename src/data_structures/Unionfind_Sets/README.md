# 并查集

## 基本介绍

1. 并查集是一种树型的数据结构，用于处理一些不相交集合（disjoint sets）的 **合并及查询** 问题。

2. 并查集通常包含两种操作

- 查找 (Find)：查询两个元素是否在同一个集合中
- 合并 (Union)：把两个不相交的集合合并为一个集合

先来看看并查集最直接的一个应用场景：**亲戚问题**。

> **题目背景**
>
> 若某个家族人员过于庞大，要判断两个是否是亲戚，确实还很不容易，现在给出某个亲戚关系图，求任意给出的两个人是否具有亲戚关系。
>
> **题目描述**
>
> 规定：x和y是亲戚，y和z是亲戚，那么x和z也是亲戚。如果x,y是亲戚，那么x的亲戚都是y的亲戚，y的亲戚也都是x的亲戚。
>
> **输入格式**
>
> 第一行：三个整数n,m,p，（n<=5000,m<=5000,p<=5000），分别表示有n个人，m个亲戚关系，询问p对亲戚关系。以下m行：每行两个数Mi，Mj，1<=Mi，Mj<=N，表示Mi和Mj具有亲戚关系。接下来p行：每行两个数Pi，Pj，询问Pi和Pj是否具有亲戚关系。
>
> **输出格式**
>
> P行，每行一个’Yes’或’No’。表示第i个询问的答案为“具有”或“不具有”亲戚关系。

这其实是一个很有现实意义的问题。我们可以建立模型，把所有人划分到若干个不相交的集合中，每个集合里的人彼此是亲戚。为了判断两个人是否为亲戚，只需看它们是否属于同一个集合即可。因此，这里就可以考虑用并查集进行维护了。

### 流程

并查集的重要思想在于，**用集合中的一个元素代表集合**。把集合比喻成**帮派**，而代表元素则是**帮主**。

![img](https://pic4.zhimg.com/80/v2-09fa3fa35e5411444b327d9cb9a31057_720w.webp)

最开始，所有大侠各自为战。他们各自的帮主自然就是自己。*（对于只有一个元素的集合，代表元素自然是唯一的那个元素）*

现在1号和3号比武，假设1号赢了（这里具体谁赢暂时不重要），那么3号就认1号作帮主*（合并1号和3号所在的集合，1号为代表元素）*。

![img](https://pic4.zhimg.com/80/v2-3bf6c1a6ecf87fa93f4dbab2012446c7_720w.webp)

现在2号想和3号比武*（合并3号和2号所在的集合）*，但3号表示，别跟我打，让我帮主来收拾你*（合并代表元素）*。不妨设这次又是1号赢了，那么2号也认1号做帮主。

![img](https://pic4.zhimg.com/80/v2-be12a6c795572d2acd77dcd49de35127_720w.webp)

现在我们假设4、5、6号也进行了一番帮派合并，江湖局势变成下面这样：

![img](https://pic1.zhimg.com/80/v2-3c353bc781c7f3553079d541a9cfdc28_720w.webp)

现在假设2号想与6号比，跟刚刚说的一样，喊帮主1号和4号出来打一架（帮主真辛苦啊）。1号胜利后，4号认1号为帮主，当然他的手下也都是跟着投降了。

![img](https://pic3.zhimg.com/80/v2-6362d8b13705d5ba17b19cdeee453022_720w.webp)

好了，比喻结束了。如果你有一点图论基础，相信你已经觉察到，这是一个**树**状的结构，要寻找集合的代表元素，只需要一层一层往上访问**父节点**（图中箭头所指的圆），直达树的**根节点**（图中橙色的圆）即可。

## 经典题目

### 省份数量

> https://leetcode.cn/problems/number-of-provinces/

有 `n` 个城市，其中一些彼此相连，另一些没有相连。如果城市 `a` 与城市 `b` 直接相连，且城市 `b` 与城市 `c` 直接相连，那么城市 `a` 与城市 `c` 间接相连。

**省份** 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 `n x n` 的矩阵 `isConnected` ，其中 `isConnected[i][j] = 1` 表示第 `i` 个城市和第 `j` 个城市直接相连，而 `isConnected[i][j] = 0` 表示二者不直接相连。

返回矩阵中 **省份** 的数量。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/12/24/graph1.jpg)

```
输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/12/24/graph2.jpg)

```
输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3
```

**提示：**

- `1 <= n <= 200`
- `n == isConnected.length`
- `n == isConnected[i].length`
- `isConnected[i][j]` 为 `1` 或 `0`
- `isConnected[i][i] == 1`
- `isConnected[i][j] == isConnected[j][i]`

#### 方法：并查集

一开始每个人都是一个集合，然后对所有人进行遍历。如果有相互认识的情况，就进行集合的合并操作（Union）。

最后返回代表节点的个数（也就是集合的个数）。

### 岛问题

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return *the number of islands*.

An **island** is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

**Example 2:**

```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
```

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`.

#### 方法一：递归

遍历整个 `matrix` 矩阵，每次遇到了 `1` 就执行一次感染过程，将与这个 `1` 连成岛屿的 `1` 全部改为 `2`。

本方法的时间复杂度为 $O(MN)$ 。

![image-20221212102703975](https://tva1.sinaimg.cn/large/008vxvgGgy1h90tgre5xkj30od0350ss.jpg)

#### 方法二：并查集

先把所有的 `1` 看成独立的集合，然后查其上面和左边的数字是否是 `1` ，是 `1` 则合并。最后返回集合个数即可。

要使用数组实现并查集，怎么做对应的？

> 将矩阵对应成向量，公式：`i * 列数 + j`
>
> ```java
> // 矩阵对应到向量
> private int index(int r, int c) {
>   return r * col + c;
> }
> ```

![image-20221212110638840](https://tva1.sinaimg.cn/large/008vxvgGgy1h90ulxzw3bj30p3047wel.jpg)

### 岛问题2

A 2d [grid](https://so.csdn.net/so/search?q=grid&spm=1001.2101.3001.7020) map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

```
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
```

Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

```
0 0 0
0 0 0
0 0 0
```

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

```
1 0 0
0 0 0   Number of islands = 1
0 0 0
```

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

```
1 1 0
0 0 0   Number of islands = 1
0 0 0
```

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

```
1 1 0
0 0 1   Number of islands = 2
0 0 0
```

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

```
1 1 0
0 0 1   Number of islands = 3
0 1 0
```

**Follow up:**

Can you do it in time complexity O(k log mn), where k is the length of the positions?

> 也就是一开始给一个 `m * n` 大小的空矩阵，然后逐步添加 `positions` 中的岛屿 `1`，要求每一步的岛数量。

#### 解法：并查集

相当于上一题的动态改法，于是不能使用递归进行感染。



## 参考

https://zhuanlan.zhihu.com/p/93647900



















