

# LeetCode189：轮转数组

## 题目描述

给你一个数组，将数组中的元素向右轮转 `k` 个位置，其中 `k` 是非负数。 

**示例 1:**

```
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
```

**示例 2:**

```
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
```

**提示：**

- `1 <= nums.length <= 105`
- `-231 <= nums[i] <= 231 - 1`
- `0 <= k <= 105`

## 分析与思路

本题主要有两个思路，分别是 `创建额外辅助数组` 以及 `数组旋转` 。

### 创建辅助数组

这个思路很简单：

![9A71D1471A31A997BC3CE7DB5DFC81E3](https://tva1.sinaimg.cn/large/008vxvgGgy1h87xqafr8nj30rp102gpt.jpg)

```java
    public void rotate1(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < 2) return;
        // 若k大于数组长度，要进行求余！
        k %= nums.length;
        int[] help = new int[nums.length];
        int i = 0;
        for (; i < nums.length - k; i++) {
            help[i + k] = nums[i];
        }
        for (int j = 0; j < k; j++) {
            help[j] = nums[j + i];
        }
        for (i = 0; i < nums.length; i++) {
            nums[i] = help[i];
        }
    }
```

复杂度分析：

- 时间复杂度： $O(N)$ 
- 空间复杂度： $O(N)$ 

### 数组旋转

这个思路就比较巧妙了：

![image-20221117104802135](https://tva1.sinaimg.cn/large/008vxvgGgy1h87xkwme4aj30bq09rmxe.jpg)

```java
    public void rotate(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < 2) return;
        // 若k大于数组长度，要进行求余！
        k %= nums.length;
        // 数组翻转！！！
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        if (end < start) return;
        int tmp = nums[start];
        while (start < end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
```

复杂度分析：

- 时间复杂度： $O(N)$ 
- 空间复杂度： $O(1)$ 







