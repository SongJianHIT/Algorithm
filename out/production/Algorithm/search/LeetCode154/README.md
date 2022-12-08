# 剑指 Offer 11. 旋转数组的最小数字

## 题目描述

https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/description/?envType=study-plan&id=lcof&plan=lcof&plan_progress=1z4mwoc

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

给你一个可能存在 **重复** 元素值的数组 `numbers` ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的**最小元素**。例如，数组 `[3,4,5,1,2]` 为 `[1,2,3,4,5]` 的一次旋转，该数组的最小值为 1。 

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` 旋转一次 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

**示例 1：**

```
输入：numbers = [3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：numbers = [2,2,2,0,1]
输出：0
```

**提示：**

- `n == numbers.length`
- `1 <= n <= 5000`
- `-5000 <= numbers[i] <= 5000`
- `numbers` 原来是一个升序排序的数组，并进行了 `1` 至 `n` 次旋转

## 分析与思路

寻找旋转数组的最小元素即为寻找 **右排序数组** 的首个元素 `nums[x]` ，称 `x` 为 **旋转点** 。

![image-20221201091025349](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o1fmb8b5j30eh0argly.jpg)

循环二分的判断：

- 当 `numbers[mid] > numbers[right]` 时，说明 `mid` 一定在左排序数组中，因此执行 `left = mid + 1`
- 当 `numbers[mid] < numbers[right]` 时，说明 `mid` 一定在右排序数组中，因此执行 `right = mid`
- 当 `numbers[mid] = numbers[right]` 时，**无法判断目前在左数组还是右数组**，因此执行 `right--`

> 本题主要对二分判断的讨论比较难，一定要讨论清楚