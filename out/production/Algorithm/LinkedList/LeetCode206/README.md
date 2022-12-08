# 206. 反转链表

## 题目描述

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。 

**示例 1：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8h2jlhehrj30f2066q33.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**示例 2：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8h2jn3x64j3052066dfq.jpg)

```
输入：head = [1,2]
输出：[2,1]
```

**示例 3：**

```
输入：head = []
输出：[]
```

**提示：**

- 链表中节点的数目范围是 `[0, 5000]`
- `-5000 <= Node.val <= 5000`

**进阶：**链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？

## 思路与分析

本题至少掌握三种方法！

### 方法一：头插法反转链表

创建一个新链表 `res` ，随后在遍历 `head` 链表的同时，使用头插法，即可完成链表反转。

![image-20221125083159506](https://tva1.sinaimg.cn/large/008vxvgGgy1h8h2lrwn9mj30wc048dfu.jpg)

### 方法二：递归（重点）

首先递归到链表尾部，得到新的头节点 `newhead` ，然后逐步回溯：

- `head.next.next = head` ，这一步完成反转
- `head.next = null` 是保证最后一个元素（就是旧的 `head` 的 `next` 指向 `null` ）

![IMG_1BF7F4B5DB38-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8h2mfx0wgj30vp0u0dkr.jpg)

### 方法三：迭代

因为要反转链表，所以需要额外的指针指向前一个元素。
