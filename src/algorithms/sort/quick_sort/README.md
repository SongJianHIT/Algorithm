# 快速排序

## 经典题型

### 荷兰国旗问题

该问题有两种问法，给定一个数组 `arr` ，以及一个目标数 `target` 要求：

- 第一种问法：小于等于 `target` 的数排在数组左边，大于 `target` 的数排在数组右边。
- 第二种问法：小于 `target` 的数排在数组左边，等于 `target` 的数排在数组中间，大于 `target` 的数排在数组右边

即，使用 `target` 去划分数组。要求：空间复杂度 $O(1)$ ，时间复杂度 $O(n)$ 

####  第一种问法解法

使用一个指针 `p` 维护一个 **小于等于目标 `target` 的区域**，对数组进行遍历：

- 若当前数 `arr[i] <= target` ：
  - 当前数 `arr[i]` 与区域指针 `p` 的下一个位置元素 `arr[p+1]` 进行交换
  - 扩展区域 `p++`
- 若当前数 `arr[i] > target`
  - 直接跳过当前元素，continue

![IMG_5CCFCE26C249-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8evporzsbj31so0q279u.jpg)

```java

```

####  第二种问法解法

思路与第一种问法类似，不同的是 **区域划分** 的区别。这里需要维护三个区域，**小于区域、等于区域 和 大于区域**。

使用两个指针 `p1` 和 `p2` ：

![IMG_06CFC776F2B8-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8evtpdrqij31270dt75j.jpg)

遍历数组，会有三种情况：

- 当前数 `arr[i] < target` ：
  - 当前数 `arr[i]` 与小于区的下一元素 `arr[p1+1]` 进行交换，小于区扩 `p1++`，当前数跳
- 当前数 `arr[i] = target` ：
  - 直接 当前数跳
- 当前数 `arr[i] > target` ：
  - 当前数 `arr[i]` 与大于区的前一个元素 `arr[p2-1]` 进行交换，大于区扩 `p2--`，**当前数停在原地（因为当前数还有没看过）**！

> 这里一定要注意大于区的处理！！

![IMG_F8CFE8F1B74C-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8ew6a6gm6j31s80nvjxb.jpg)