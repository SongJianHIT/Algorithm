# 15. 三数之和

## 题目描述

给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请你返回所有和为 `0` 且不重复的三元组。

**注意：** 答案中不可以包含重复的三元组。

**示例 1：** 

```
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
```

**示例 2：** 

```
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
```

**示例 3：** 

```
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
```

**提示：** 

- `3 <= nums.length <= 3000`
- `-105 <= nums[i] <= 105`

## 分析与思路

本题的解决方法是 `排序+双指针` 。

### 从“不重复”出发

如果我们直接使用三层循环遍历，暂且不说它的时间复杂度为 $O(N^3)$ ，还会有什么结果呢？举个例子：

```java
[0, 0, 0, 0,..., 0]
```

假设对于一个全是 `0` 的数组，通过三层循环，会产生多个相同的结果。因此，我们还需要使用哈希表进行去重操作，得到不包含重复三元组的最终答案，又消耗了大量的空间。这个做法的时间复杂度和空间复杂度都很高，因此我们要换一种思路来考虑这个问题。

**不重复的本质是什么** ？我们保持三重循环的大框架不变，只需要保证：

- 第二重循环枚举到的元素 **不小于** 当前第一重循环枚举到的元素；
- 第三重循环枚举到的元素 **不小于** 当前第二重循环枚举到的元素。

也就是，我们所枚举到的三元组 $(a,b,c)$ 需要满足 $a \le b \le c$ ，就能够保证不会出现 $(b,a,c)$ 等重复形式。

同时，**对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复** 。举个例子，如果排完序的数组为

```
[0, 1, 2, 2, 2, 3]
 ^  ^  ^
```

我们使用三重循环枚举到的第一个三元组为 $(0,1,2)$ ，如果第三重循环继续枚举下一个元素，那么仍然是三元组 $(0,1,2)$ ，产生了重复。因此我们需要 **将第三重循环「跳到」下一个不相同的元素**，即数组中的最后一个元素 3，枚举三元组 $(0,1,3)$ 。

注意：**对每一层循环都都要保证相邻两次枚举元素不同！**

```java
nums.sort()
for first = 0 .. n-1
    // 只有和上一次枚举的元素不相同，我们才会进行枚举
    if first == 0 or nums[first] != nums[first-1] then
        for second = first+1 .. n-1
            if second == first+1 or nums[second] != nums[second-1] then
                for third = second+1 .. n-1
                    if third == second+1 or nums[third] != nums[third-1] then
                        // 判断是否有 a+b+c==0
                        check(first, second, third)
```

> 排序后，可以保证去重了，但如何优化时间复杂度呢？

### 双指针优化内层循环

我们这样分析，在三层循环的结构中：

- 如果我们固定了前两个元素 `a,b`，那么只有唯一的 `c` 满足 `a+b+c=0` 。
- 当第二层循环中，`b` 向后移动到不同元素 $b^\prime$ 时，此时 $b < b^\prime$ 。如果想要满足 $a+b^\prime+c^\prime=0$ ，就需要 $c>c^\prime$ 。即 $c^\prime$ 在数组中一定出现在 $c$ 的左侧。
- 我们可以从小到大枚举 $b$ ，**同时** 从大到小枚举 $c$ ，即 **第二重循环和第三重循环实际上是并列的关系** 。

具体步骤：

- 首先，数组排序
- 第一层循环，当前数组首元素 `nums[i]` ：
  - 左指针指向 `nums[i+1]` ，需要从前往后遍历
  - 右指针指向 `nums[nums.length-1]` ，需要从后往前遍历
- 遍历：
  - 若 `nums[i] = nums[i-1]` ，直接跳过，**去重**
  - 若 `nums[i] > 0` ，由于后面的数更大，因此不可能求和为 0，直接终止循环
    - 若 `sum=0` ：
      - 若 `nums[L]=nums[L+1]` ，会导致结果重复， `L++` ，**去重**
      - 若 `nums[R] = nums[R-1]` ，会导致结果重复， `R--` ，**去重**
    - 若 `sum < 0` ，则说明三数之和还不够大，把左指针右移动，即 `L++` 。
    - 若 `sum > 0` ，则说明三数之和过于大了，把右指针左移动，即 `R--` 。

> 【补充】JAVA两个常用方法：
>
> ```java
> Arrays.sort(nums); // 对数组排序
> Arrays.asList(nums); //将数组转化成 List 集合
> ```

## 代码

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;
        // 排序
        Arrays.sort(nums);
        // 标志元素 nums[i]
        for (int i = 0; i < nums.length; i++) {
            // 如果当前标志元素大于0，则无需遍历，因为后面的肯定更大
            if (nums[i] > 0) break;
            // 标志元素去重
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = nums.length - 1;
            // 双指针遍历
            while (L < R) {
                int sum = nums[L] + nums[R] + nums[i];
                if (sum == 0) {
                    // 符合题意
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 去重
                    while (L < R && nums[L + 1] == nums[L]) L++;
                    while (L < R && nums[R - 1] == nums[R]) R--;
                    L++;
                    R--;
                } else if (sum < 0) {
                    // sum 小于 0，移动左指针
                    L++;
                } else if (sum > 0) {
                    // sum 大于 0，移动右指针
                    R--;
                }
            }
        }
        return ans;
    }
}
```





## REFERENCE

https://leetcode.cn/problems/3sum/solutions/284681/san-shu-zhi-he-by-leetcode-solution/









