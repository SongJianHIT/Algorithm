# 归并排序（Merge Sort）

![image-20221115161249057](https://tva1.sinaimg.cn/large/008vxvgGgy1h85wbazgsmj30j70e43zc.jpg)

## 归并排序的基本思想

我们的需求是让整个数组 `arr[L, R]` 上有序，那么我们可以进行以下四步：

- 求数组中点 `middle`
- 让 `arr[L, middle]` 有序；
- 让 `arr[middle + 1, R]` 上有序
- 合并左右两部分数组

## 归并排序的复杂度

由于使用了递归，因此时间复杂度可以根据 `Master` 公式进行计算：

- 总数据量为 $N$ 
- 子问题涉及最多数据量为 $N / 2$ ，故 $b = 2$ 
- 共调用两次子问题，故 $a=2$
- 其余时间复杂度（Merge）为 $O(N)$ 

故，归并排序的时间复杂度为 $O(NlogN)$ 。

额外空间复杂度为 $logN$。

> 所有 $O(N^2)$ 大量浪费了比较行为，而归并没有浪费比较行为。

## 归并排序的两种实现形式

### 递归实现

```java
	public static void mergeSort_recur(int[] arr) {
        if (arr == null || arr.length < 2)  return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    public static void merge(int[] arr, int L, int mid, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        // 辅助数组指针
        int i = 0;
        // 第一个有序数组的起始位置
        int p1 = L;
        // 第二个有序数组的起始位置
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            // 两个指针都没越界
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while(p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 将辅助数组 help 拷贝回 arr
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
```

### 非递归实现

```java
	public static void mergeSort_non_recur(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // mergeSize 为步长（可理解为半径）
        int mergeSize = 1;
        while (mergeSize < N) {     //logN
            // L：当前左组的第一个位置
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) break;
                // 如果右边界越界，则拿数组右边界为 R
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            /**
             *  防止溢出！！
             *  如果数组长度很接近 Integer.MAX_VALUE，
             *  他在 N/2 时还没溢出，但乘 2 之后可能会溢出
             *  溢出之后就变成负数，仍会满足循环条件！！！
             */
            if (mergeSize > N / 2) break;
            mergeSize <<= 1;
        }
    }
```

## 归并排序常见题型

> MergeSort 把 **需要比较** 的东西变有序，而有序能够加速问题求解！

### 小和问题

在一个数组中，**每一个数左边比当前数小的数累加起来**，叫做这个数组的 `小和` 。

例如，想要求解数组 `[1,3,4,2,5] `的小和：

- 1 左边比 1 小的数：没有 
- 3 左边比 3 小的数：1
- 4 左边比 4 小的数：1, 3
- 2 左边比 2 小的数：1
- 5 左边比 5 小的数：1, 3, 4, 2

所以小和为 `1+1+3+1+1+3+4+2=16`

>  $O(N^2)$ 的求解方式很直接，逐个遍历即可。如何将其优化到 $O(NlogN)$ 呢？

我们可以换个思路。对于某个数 `x` ，我们可以求这个数的右边有多少数比 `x` 大（假设有 `k` 个），然后直接计算 `k * x` 即可求出 `x` 在这个数组上产生的小和。例如： 

- 1的右侧有4个数比1大：则 sum += 1 * 4 
- 3的右侧有2个数比3大：则 sum += 3 * 2
- 4的右侧有1个数比4大：则 sum += 4 * 1
- 2的右侧有1个数比2大：则 sum += 2 * 1

最后 sum = 16。这里也就是另一种 $O(N^2)$ 的思路。**但是，这里会存在许多无意义的比较！！** 可以使用 Merge 的思路优化这个过程。

Merge 过程分为了左、右两组：

- 拷贝右组元素时，即右组元素比左组元素小，不产生小和
- **拷贝左组元素时，即左组元素比右组元素小，产生小和**
- 相等的时候，先拷贝右边的，

![IMG_5920](https://tva1.sinaimg.cn/large/008vxvgGgy1h85xx9235wj30u00wegob.jpg)

这为什么更快？每次归并的过程，记录了当前产生的小和，并且使数组局部有序。

因此，**左组右组都是局部有序的**，当拷贝左组元素时（说明左组元素更小），计算小和，就可以直接把右组当前包括之后的元素数量直接求出（**避免了一层遍历**）。因为此时，被拷贝进 `help` 数组的左组元素一定小于右组剩下的所有的元素，这个数量计算出来也就是 `k` 。

### 数组中的逆序对

> https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

**示例 1:**

```
输入: [7,5,6,4]
输出: 5
```

**限制：**

```
0 <= 数组长度 <= 50000
```

**方法一：暴力遍历**

```java
public int reversePairs(int[] nums) {
  int ans = 0;
  for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
              ans += nums[i] > nums[j] ? 1 : 0;
            }
      }
  return ans;
}
```

时间复杂度 $O(N^2)$ ，LeetCode上直接超时。

**方法二：归并排序思想**

在 Merge 过程中，有几种情况：

- 当 `p1 = mid + 1` 时，代表左子数组已合并完，因此添加右子数组当前元素 `help[i++] = nums[p2++];`
- 当 `p2 = r + 1` 时，代表右子数组已合并完，因此添加左子数组当前元素 `help[i++] = nums[p1++];`
- 否则，当 `p1 <= middle && p2 <= r` 时：
  - 若 `nums[p1] < nums[p2]` 时，`help[i++] = nums[p1++]` ，没有逆序
  - 若 `nums[p1] > nums[p2]` 时，`help[i++] = nums[p2++]` ，记录逆序数 `ans += mid - p1 + l;`
  - 当 `nums[p1] = nums[p2]` 时，`help[i++] = nums[p1++]` 

![image-20221115201850217](https://tva1.sinaimg.cn/large/008vxvgGgy1h862u5voj0j30af094wem.jpg)

### Bigger Than Right Twice

定义：**对于每一个数来讲，大于右边的数的两倍**
分析：用归并排序，先完成计数再进行归并。在每次计数时，循环遍历左组元素，在每次遍历中，尽可能地在右组中向右滑，直到不满足题目要求。

## REFERENCE

1. https://leetcode.cn/circle/article/zeM9YK/
2. https://blog.csdn.net/ymhdt/article/details/123937377





