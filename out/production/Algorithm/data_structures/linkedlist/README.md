# 数据结构：链表

## 1 基本介绍

### 1.1 单链表

单链表（Single Linked List）在算法中非常常见了。Java 形式的单链表类可实现如下：

```java
public static class ListNode {
		public int value;
		public ListNode next;
		public ListNode(int data) {
				value = data;
    }
}
```

### 1.2 双向链表

Java 形式的双向链表类可实现如下：

```java
public static class DoubleListNode {
		public int value;
  	    public ListNode last;
		public ListNode next;
		public ListNode(int data) {
				value = data;
    }
}
```

### 1.3 链表方法论

- 对于笔试：不用在乎空间复杂度，一切为了时间复杂度
- 对于面试：时间复杂度依然放在第一位， **但是一定要找到空间最省方法**

### 1.4 链表题常用的数据结构与技巧

-  **使用容器** ：哈希表、数组等
-  **快慢指针** 

## 2 常见题型

### 2.1 单链表的反转

> LeetCode：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/

给定单链表的头节点 `head` ，请反转链表，并返回反转后的链表的头节点。

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h84qhffgkfj30f2066q33.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**思路**

在遍历链表时，将当前节点的 `next` 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。

**复杂度分析**

- 时间复杂度： $O(n)$ ，其中 $n$ 是链表的长度，需要遍历链表一次
- 空间复杂度： $O(1)$ 

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

### 2.2 删除链表的节点

> LeetCode：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

**算法流程：**

- 特例处理： 当应删除头节点 `head` 时，直接返回 `head.next` 即可。
- 初始化： `pre = head , cur = head.next` 。
- 定位节点： 当 `cur` 为空 或 `cur` 节点值等于 `val` 时跳出。
- 保存当前节点索引，即 `pre = cur` 。
- 遍历下一节点，即 `cur = cur.next` 。
- 删除节点： 若 `cur` 指向某节点，则执行 `pre.next = cur.next` ；若 `cur` 指向 `null` ，代表链表中不包含值为 `val` 的节点。
- 返回值： 返回链表头部节点 `head` 即可。

复杂度分析：

- 时间复杂度 $O(N)$ ： $N$ 为链表长度，删除操作平均需循环 $N/2$ 次，最差 $N$ 次。
- 空间复杂度 $O(1)$： `cur, pre` 占用常数大小额外空间。

```java
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        while (head != null) {
            if (head.val != val) break;
            head = head.next;
        }
        ListNode pre = head, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
```

### 2.3 链表中点相关

> 技巧：快慢指针

分为四个小题，在单链表中：

1. 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
2. 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
3. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
4. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个

### 2.4 回文链表

> https://leetcode.cn/problems/aMhZSa/

给定一个链表的 **头节点** `head` **，**请判断其是否为回文链表。

如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。

**示例 1：**

![image-20221130094739710](https://tva1.sinaimg.cn/large/008vxvgGgy1h8mww1rebnj30ct024q2v.jpg)

```
输入: head = [1,2,3,3,2,1]
输出: true
```

**示例 2：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8mwv2ij9rj303u01qmwy.jpg)

```
输入: head = [1,2]
输出: false
```

**提示：**

- 链表 L 的长度范围为 `[1, 105]`
- `0 <= node.val <= 9`

#### 使用容器方法

具体步骤：

- 创建一个栈
- 遍历一次链表，边遍历，边入栈
- 再次遍历链表，边遍历，边弹栈，进行对比
  - 如果出栈元素与链表元素不等，则不是回文结构

#### 不使用容器方法

具体步骤：

- 快慢指针找到链表中点
  - 奇数链表找中点
  - 偶数链表找上中点
- 中点后的那一半链表进行反转
- 从两端开始遍历，逐一对比
  - 只要有一个为空，停止
- 恢复原来链表

### 2.5 链表分区

将单链表按某值划分为左边小、中间相等、右边大的形式。

#### 使用容器方法

把链表放入数组，然后执行 `partition` 即可。

#### 不使用容器方法

创建 6 个指针：

- 小于区：小于区头指针、尾指针
- 等于区：等于区头指针、尾指针
- 大于区：大于区头指针、尾指针

随后，遍历原链表，根据 `cur` 的值进行对应区域的添加。

最后，把各区域首尾相连，返回小于区域的头指针。

![IMG_FE71FA562902-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o2p5djc5j31a40u0dmp.jpg)

> 类似题目：https://leetcode.cn/problems/partition-list-lcci/

### 2.6 复杂链表的复制

请实现 `copyRandomList` 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 `next` 指针指向下一个节点，还有一个 `random` 指针指向链表中的任意节点或者 `null`。

**示例 1：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o4iuccujj31gs0aq400.jpg)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**示例 2：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o4iq0l9aj31ae07kt95.jpg)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

**示例 3：**

**![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o4isen67j31gi096my0.jpg)**

```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

**示例 4：**

```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

**提示：**

- `-10000 <= Node.val <= 10000`
- `Node.random` 为空（null）或指向链表中的节点。
- 节点数目不超过 1000 。

#### 使用容器方法

使用 `HashMap` ，key 是老节点，value 是新节点。

第一次遍历，将老节点和新节点放入表 `HashMap`

第二次遍历，设置其 `random` 和 `next` 指针。

#### 不使用容器方法

具体步骤：

- 遍历链表，在遍历过程中创建复制节点，插入老链表
- 遍历链表，一次遍历两个节点（新和老节点），设置 `random` 指针
- 遍历链表，新、老链表分离

![IMG_AD54EAA4E363-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8o5fnwiqzj30xt0u042x.jpg)

### 2.7 链表中环的入口节点

如果单链表有环，则返回入环节点；如果无环，则返回 `null`。

> https://leetcode.cn/problems/c32eOV/

#### 使用容器方法

使用 `HashSet` 来保存节点，遍历单链表。如果有重复节点，则第一个重复节点为入环节点，返回。

#### 不使用容器的方法

**快慢指针方法！** 快指针一次走两步，慢指针一次走一步：

- 如果快指针走到 `null`，则一定无环
- 如果有环，则快慢指针一定会相遇（ **相遇的位置可能不是入环节点处** ）
- 相遇后
  - 快指针回到 `head`
  - 快指针恢复为一次走一步，慢指针仍然一次走一步
- 快慢指针一定会在入口节点相遇！

### 2.8 两个单链表相交

给定两个可能有环也可能无环的单链表，头节点 `head1` 和头节点 `head2`。请实现一个函数，如果两个链表相交，则返回相交的第一个节点。如果不相交，则返回null。

要求：如果两个链表的长度之和为 $N$，时间复杂度限制为 $O(N)$ ，空间复杂度为 $O(1)$ 。

#### 不使用容器的方法

**情况一** ：`head1` 无环， `head2` 无环

- 使用 `HashSet` 记录节点
- 遍历 `head1` ，添加入 `HashSet`
- 遍历 `head2` ，边添加，边检查集合中是否重复即可

**情况二** ：其中一个链表有环，另一个无环

- 一定不相交！

**情况三** ：`head1` 有环， `head2` 有环

- 

#### 使用容器的方法

**情况一** ：`head1` 无环， `head2` 无环

- 遍历 `haed1` ，走到 `end1` （链表末尾），记录链表长度 `length1` （假设为100）
- 遍历 `haed2` ，走到 `end2` （链表末尾），记录链表长度 `length2` （假设为80）
- 如果 `end1 != end2` （内存地址不等），则一定不相交
- 如果 `end1 == end2` （内存地址相等），则两条链表都 **从头开始遍历** ：
  - 让 链表1 先走 20 步（长度之差，100-80）
  - 然后 链表1 和 链表2 一起走
  - 他们一定会在相交的地方相遇

**情况二** ：其中一个链表有环，另一个无环

- 一定不相交！

**情况三** ：`head1` 有环， `head2` 有环。则共有三种子情况：

- `head1` 和 `head2` 不相交
- `head1` 和 `head1` 相交，且 **具有相同的入环节点**
- `head1` 和 `head2` 相交，且 **入环节点不相同**

![IMG_96A1C4966714-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8p9z6bdedj31om0rrgpz.jpg)

所以，针对情况三：

- 如果 `loop1==loop2`，对应子情况2:
  - 把 `loop1` 或 `loop2` 作为终止节点
  - 转化为求解 **两个无环单链表的第一个相交节点**
- 如果 `loop1!=loop2`，则可能是子情况1，也可能是子情况3：
  - 从 `loop1` 开始出发遍历
    - 如果回到 `loop1` ，并没有遇到 `loop2` ，则为子情况1
    - 如果回到遇到 `loop2` ，则为子情况3

