# 剑指 Offer 41. 数据流中的中位数

## 题目描述

> https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/description/?envType=study-plan&id=lcof&plan=lcof&plan_progress=1z4mwoc

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

- void addNum(int num) - 从数据流中添加一个整数到数据结构中。
- double findMedian() - 返回目前所有元素的中位数。

**示例 1：**

```
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```

**示例 2：**

```
输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]
```

**限制：**

- 最多会对 `addNum、findMedian` 进行 `50000` 次调用。

## 解法：堆

建立一个小根堆 `queueMin` 和一个大根堆 `queueMax`。规定：

- 大根堆用来保存有序数组中较大部分元素，长度为 `N/2` 或 `(N+1)/2`
- 小根堆用来保存较小部分元素，长度为 `N/2` 或 `(N-1)/2`

随后，中位数可以通过大根堆和小根堆的堆顶元素直接计算得到。

<img src="https://tva1.sinaimg.cn/large/008vxvgGgy1h91z3te37ij30vp0nsmyu.jpg" alt="25837f1b195e56de20587a4ed97d9571463aa611789e768914638902add351f4-Picture1" style="zoom:50%;" />

添加元素时：

- 当两个堆的大小相同时，优先放入小根堆（较大部分）
- 当两个堆大小不同时，为了平衡，则需要放入大根堆（较小部分）

每次放入元素时，为了保持次序，需要先进入另外一个堆，调整次序后，在 `poll` 到原来的堆中。

**时间复杂度** ：

-  查找中位数： $O(1)$ 
- 添加数字： $O(logN)$ 

**空间复杂度** ：$O(N)$

