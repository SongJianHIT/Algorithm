# 45. 跳跃游戏 II

## 题目描述

给你一个非负整数数组 `nums` ，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

假设你总是可以到达数组的最后一个位置。 

**示例 1:**

```
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

**示例 2:**

```
输入: nums = [2,3,0,1,4]
输出: 2
```

**提示:**

- `1 <= nums.length <= 104`
- `0 <= nums[i] <= 1000`

## 分析与思路

### 方法一：动态规划

使用 `dp` 数组记录到达当前 `i` 位置，所需最短的跳步数。最短的跳步数是当前跳步数 `dp[i]` 和历史跳步数 `dp[i-k] + 1` 之中的最小值。

```java
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] dp = new int[nums.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[dp.length - 1];
    }
}
```

![image-20221123093302862](https://tva1.sinaimg.cn/large/008vxvgGgy1h8et4q8jkqj30we03zt8q.jpg)

时间复杂度： $O(n^2)$ ，其中 n 是数组长度。

空间复杂度： $O(n)$ 。

### 方法二：贪心

使用贪心，正向查找。

如果某一个作为 **起跳点** 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。 可以对每一个能作为 **起跳点** 的格子都尝试跳一次，把 **能跳到最远的距离** 不断更新。

如果从这个 **起跳点** 起跳叫做第 1 次 **跳跃**，那么从后面 3 个格子起跳 **都** 可以叫做第 2 次 **跳跃**。

所以，当一次 **跳跃** 结束时，从下一个格子开始，到现在 **能跳到最远的距离**，都 是下一次 **跳跃** 的 **起跳点**。 对每一次 **跳跃** 用 for 循环来模拟。

跳完一次之后，更新下一次 **起跳点** 的范围。

在新的范围内跳，更新 **能跳到最远的距离**。

记录 **跳跃** 次数，如果跳到了终点，就得到了结果。

![9d5016c6e660a452991185d23b7b4d98853b7c300453d79715b5e9a206085e44-图片](https://tva1.sinaimg.cn/large/008vxvgGgy1h8et8utv7xj30c40bkgmm.jpg)

```java
public int jump(int[] nums) {
  // start, end 分别记录跳跃点的范围
  int start = 0, end = 1, steps = 0;
  while (end < nums.length) {
    // 记录当前跳跃点区间能够到达最远的距离
    int maxPos = 0;
    for (int i = start; i < end; i++) {
      maxPos = Math.max(maxPos, i + nums[i]);
    }
    start = end;
    end = maxPos + 1;
    steps++;
  }
  return steps;
}
```

