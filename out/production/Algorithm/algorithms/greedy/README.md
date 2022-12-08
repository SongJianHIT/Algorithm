# 贪心算法

## 基本介绍

贪心算法：

- 最自然智慧的算法
- 用一种局部最功利的标准，总是做出在当前看来是最好的选择
- 难点在于证明局部最功利的标准可以得到全局最优解
- 对于贪心算法的学习主要以增加阅历和经验为主

贪心策略的成败就看比较的标准。

## 经典题型

### 字典序最小结果

给定一个由字符串组成的数组 `strs` ，必须把所有的字符串拼接起来，返回所有可能的拼接结果中，字典序最小的结果。

### 会议室安排

（1）对于每个会议 `i` ，起始时间 `bi`和结束时间 `ei` ，且 `bi` < `ei`

（2）`[bi,ei)` 与 `[bj,ej)` 不相交，则会议 `i` 和会议 `j` 相容，` bi≥ej` 或 `bj≥ei`

目标：在有限的时间内，尽可能多地安排会议。

贪心策略：

- 选最早开始的会议 （缺点：会议可能持续时间长）
- 选持续时间最短的会议（缺点：会议可能开始地晚）
- **选择最早结束的会议**

### 金条切割（哈夫曼编码问题）

一块金条切成两半，是需要花费和长度数值一样的铜板的。

比如：长度为20的金条，不管切成长度多大的两半，都要花费20个铜板一群人想整分整块金条，怎么分最省铜板？

> 例如，给定数组 `{10,20,30}` ，代表一共三个人，整块金条长度为 `10+20+30=60` 。金条要分成 `10,20,30` 三个部分。 如果，先把长度 `60` 的金条分成 `10` 和 `50`，花费 `60` 再把长度 `50` 的金条分成20和30，花费 `50` 。一共花费 `110` 铜板。 但是如果，先把长度 `60` 的金条分成 `30` 和 `30` ，花费 `60` 再把长度 `30` 金条分成 `10` 和 `20` ，花费 `30`，一共花费 `90` 铜板。 输入一个数组，返回分割的最小代价。

本质上可以归结为 **求构成一个二叉树且二叉树非叶节点值的和最小**，这里哈夫曼树可以满足要求。所以我们只需要利用 **最小堆**，每次从中提取两次最小值，然后将求和结果重新放回这个堆中，直到堆中只剩一个元素。
























