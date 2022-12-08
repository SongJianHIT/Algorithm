# 二分搜索

## 概要

Binary Search，即二搜索（查找）。每轮搜索将搜索的数组砍半，以节省搜索时间，通常时间复杂度为 $O(logN)$。常用于 **有序数组的搜索** 中！

LeetCode 上有很多二分搜索的题，基本上都带有 **“有序”** 二字，因此需要格外注意！

但实际上，能够使用二分搜索的情况：

- **数据状况特殊**：如有序数组
- **问题本身特殊**：如局部最小值

如果能够构建起 **排他性** 的东西（一半有，一半没有），就可以使用二分。

## 常见的应用

通常，二分搜索可以解决以下问题：

- 在一个有序数组中，找某个值是否存在

- 在一个有序数组中，找 $\ge$ 某个数最左侧的位置

  > 如：`[1,3,4,5,5,5,7,8]` 中找 $\ge$ 5 最左侧的位置，返回 index 为 3。 

- 在一个有序数组中，找 $\le$ 某个数最右侧的位置

- **无序数组** 中的局部最小值

  > 无序数组中，任意两个相邻的数不相等。所谓局部最小，即 `arr[i-1] > arr[i] < arr[i+1]`，此时 `arr[i]` 称为局部最小值。

前三个比较好理解，关键是第四个问题怎么解决？

## 经典题型

### 无序数组中的局部最小值

无序数组中，任意两个相邻的数不相等。所谓局部最小，即 `arr[i-1] > arr[i] < arr[i+1]`，此时 `arr[i]` 称为局部最小值。只需要求出任一个局部最小值即可。

第一步，我们先检查数组两端的元素，则出现三种情况。

![IMG_5912](https://tva1.sinaimg.cn/large/008vxvgGgy1h83muz0yrnj31t30u0dmd.jpg)

第二步，我们找到 `arr[mid]`，让它与 `arr[mid-1]` 和 `arr[mid+1]` 比较，然后根据结果进行返回，或者进一步二分。

![IMG_5913](https://tva1.sinaimg.cn/large/008vxvgGgy1h83mw5v0kxj31720u0wiv.jpg)

本题的题目特点，即 **局部最小的趋势**，是能够使用二分的关键！！

### 寻找正序数组中第k小的数

给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **第k小的数** 。算法的时间复杂度应该为 `O(log (m+n))` 。

**示例 1：**

```
输入：nums1 = [1,3,4,9], nums2 = [1,2,3,4,5,6,7,8,9,10], k = 7
输出：4
解释：合并数组 = [1,1,2,3,3,4,4,...]，第7小的数为4
```

#### 解题思路1：先合并在返回

创建一个新数组，然后合并两个有序数组，直接取 `nums[K-1]` 即可。

时间复杂度：$O(n+m)$；空间复杂度：$O(n+m)$。

#### 解题思路2：二分查找

时间复杂度：$O(log(n+m))$；空间复杂度：$O(1)$。

举个例子，思路如下：

![IMG_5909](https://tva1.sinaimg.cn/large/008vxvgGgy1h86zr9s26mj30u00yftcb.jpg)

```java
class Solution {
    
    public double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
        /**
            递归实现，在两个有序数组中找到第 k 小的数
         */
         // 1、判断两个数组还剩下多少元素
        int len_1 = end1 - start1 + 1;
        int len_2 = end2 - start2 + 1;
        
        // 2、统一转化为 len1 为元素少的数组，方便后续处理，不用额外讨论
        if(len_1 > len_2) return getKth(nums2, start2, end2, nums1, start1, end1, k);

        // 3、此时，如果有数组元素个数为0，只可能是 nums1 了
        //    那么，只需要返回 nums2 中第 k 小元素即可
        if(len_1 == 0) return nums2[start2 + k - 1];

        // 4、如果是取第 1 小的元素，那可以直接判断了
        if(k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 5、到这里，说明两个数组都还有元素并且 k != 1，准备递归处理
        //    先拿到 k/2 元素进行对比
        //    防止取出的元素索引大于数组长度
        int index1 = start1 + Math.min(len_1, k/2) - 1;
        int index2 = start2 + Math.min(len_2, k/2) - 1;

        if(nums1[index1] >= nums2[index2]){
            // 删除 nums2 的前面元素
            return getKth(nums1, start1, end1, nums2, index2 + 1, end2, k - (index2 - start2 + 1));
        }else{
            // 删除 nums1 的前面元素
            return getKth(nums1, index1 + 1, end1, nums2, start2, end2, k - (index1 - start1 + 1));
        }
    }
}
```

### 寻找两个正序数组的中位数

> 这个题放在这里就是要跟上面对比，**本题可直接规约到上题中**。

给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。算法的时间复杂度应该为 `O(log (m+n))` 。 

**示例 1：**

```
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
```

#### 解题思路：二分查找

可以将数组的中位数看作取第k小的数！只是比上题多了最后一步对奇偶长度数列的处理而已。

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 两个中位数的位置+1（这里是索引+1，因为对应第 k 个）
        // 统一奇偶，奇：pos1 = pos2；
        int pos1 = (len1 + len2 + 1) / 2;
        int pos2 = (len1 + len2 + 2) / 2; 
        return (getKth(nums1, 0, len1-1, nums2, 0, len2-1, pos1) + getKth(nums1, 0, len1-1, nums2, 0, len2-1, pos2)) * 0.5;
    }
    private double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
        /**
            递归实现，在两个有序数组中找到第 k 小的数
         */
         // 1、判断两个数组还剩下多少元素
        int len_1 = end1 - start1 + 1;
        int len_2 = end2 - start2 + 1;
        
        // 2、统一转化为 len1 为元素少的数组，方便后续处理，不用额外讨论
        if(len_1 > len_2) return getKth(nums2, start2, end2, nums1, start1, end1, k);

        // 3、此时，如果有数组元素个数为0，只可能是 nums1 了
        //    那么，只需要返回 nums2 中第 k 小元素即可
        if(len_1 == 0) return nums2[start2 + k - 1];

        // 4、如果是取第 1 小的元素，那可以直接判断了
        if(k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 5、到这里，说明两个数组都还有元素并且 k != 1，准备递归处理
        //    先拿到 k/2 元素进行对比
        //    防止取出的元素索引大于数组长度
        int index1 = start1 + Math.min(len_1, k/2) - 1;
        int index2 = start2 + Math.min(len_2, k/2) - 1;

        if(nums1[index1] >= nums2[index2]){
            // 删除 nums2 的前面元素
            return getKth(nums1, start1, end1, nums2, index2 + 1, end2, k - (index2 - start2 + 1));
        }else{
            // 删除 nums1 的前面元素
            return getKth(nums1, index1 + 1, end1, nums2, start2, end2, k - (index1 - start1 + 1));
        }
    }
}
```

