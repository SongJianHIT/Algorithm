# 18. 四数之和

## 题目描述

给你一个由 `n` 个整数组成的数组 `nums` ，和一个目标值 `target` 。请你找出并返回满足下述全部条件且**不重复**的四元组 `[nums[a], nums[b], nums[c], nums[d]]` （若两个四元组元素一一对应，则认为两个四元组重复）：

- `0 <= a, b, c, d < n`
- `a`、`b`、`c` 和 `d` **互不相同**
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

你可以按 **任意顺序** 返回答案 。

**示例 1：**

```
输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
```

**示例 2：**

```
输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]
```

**提示：**

- `1 <= nums.length <= 200`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`

## 思路与分析

本题与三数之和一样，都是 `排序 + 双指针` 型考点。思路也很像，只不过三数之和是将 $O(N^3)$ 降到 $O(N^2)$ ，本题是将 $O(N^4)$ 降至 $O(N^3)$ 。

同样需要排除重复元素，也就是每次循环需要先判断：

- 如果当前元素与上个循环元素相同，直接 `continue` 
- 如果当前元素与上个元素不同，则可继续讨论

> 需要特别注意的是： **在求和过程中很容易出现溢出，所以一定要使用 `long` 来接一下！**

### 本题可以做额外的剪枝操作

- 第一层循环时（确定了一个数），可计算 `nums[p1]+nums[p1+1]+nums[p1+2]+nums[p1+3]` ：
  - 若该数 **大于** target，则后面无需再遍历了，往后只会越来越大，直接 `break` 即可
- 第一层循环时（确定了一个数），可计算 `nums[p1]+nums[length-1]+nums[length-2]+nums[length-3]` ：
  - 若该数 **小于** target，则该层循环无需进行，该数已经是当前循环最大的数，直接 `continue` 即可
- 第二层循环中（确定了两个数），可计算 `nums[p1]+nums[p2]+nums[p2+1]+nums[p2+2]` ：
  - 若该数 **大于** target，同第一条，可直接 `break` 当前循环
- 第二层循环中（确定了两个数），可计算 `nums[p1]+nums[p2]+nums[length-1]+nums[length-2]` ：
  - 若该数 **小于** target，同第二条，可直接 `	continue` 当前循环

### 复杂度分析

- 时间复杂度为 $O(N^3)$ 
- 空间复杂度为 $O(logN)$ ，空间复杂度主要取决于 **排序** 额外使用的空间。

## 代码

```java
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        int p1, p2, p3, p4;
        // 遍历取第一个数
        for (p1 = 0; p1 <= len - 4; p1++) {
            // 去重判断
            if (p1 > 0 && nums[p1] == nums[p1 - 1]) continue;
            // 剪枝
            if ((long) nums[p1] + (long) nums[p1+1] + (long) nums[p1+2] + (long) nums[p1+3] > target) {
                break;
            }
            if ((long) nums[p1] + (long) nums[len-1] + (long) nums[len-2] + (long) nums[len-3] < target) {
                continue;
            }
            // 遍历取第二个数
            for (p2 = p1 + 1; p2 <= len - 3; p2++) {
                // 去重判断
                if (p2 > p1 + 1 && nums[p2] == nums[p2 - 1]) {
                    continue;
                }
                // 剪枝
                if ((long) nums[p1] + (long) nums[p2] + (long) nums[p2 + 1] + (long) nums[p2 + 2] > target) {
                    break;
                }
                if ((long) nums[p1] + (long) nums[p2] + (long) nums[len - 1] + (long) nums[len - 2] < target) {
                    continue;
                }
                // 双指针进行第三层循环
                p3 = p2 + 1;
                p4 = len - 1;
                while (p3 < p4) {
                    if ((long) nums[p1] + (long) nums[p2] + (long) nums[p3] + (long) nums[p4] == target) {
                        // 添加结果
                        res.add(Arrays.asList(nums[p1], nums[p2], nums[p3], nums[p4]));
                        // 去重
                        while (p3 < p4 && nums[p3] == nums[p3 + 1]) p3++;
                        while (p3 < p4 && nums[p4] == nums[p4 - 1]) p4--;
                        p3++;
                        p4--;
                    } else if ((long) nums[p1] + (long) nums[p2] + (long) nums[p3] + (long) nums[p4] < target) {
                        p3++;
                    } else {
                        p4--;
                    }
                }
            }
        }
        return res;
    }
}
```

![image-20221119092647551](https://tva1.sinaimg.cn/large/008vxvgGgy1h8a6h26r19j30px05n3yo.jpg)