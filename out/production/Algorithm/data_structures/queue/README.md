# 数据结构：队列

## 1 基本结构介绍



## 2 常见题型

### 2.1 使用定长数组实现队列

如何使用一个定长数组，实现队列 FIFO 的数据结构？

思路：使用 **循环队列（RingArray）**

可见文件 `RingArray.java` 具体实现。

### 2.2 使用队列实现栈

> LeetCode：https://leetcode.cn/problems/implement-stack-using-queues/

请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（`push`、`top`、`pop` 和 `empty`）。

实现 `MyStack` 类：

- `void push(int x)` 将元素 x 压入栈顶。
- `int pop()` 移除并返回栈顶元素。
- `int top()` 返回栈顶元素。
- `boolean empty()` 如果栈是空的，返回 `true` ；否则，返回 `false` 。

**注意：**

- 你只能使用队列的基本操作 —— 也就是 `push to back`、`peek/pop from front`、`size` 和 `is empty` 这些操作。
- 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。

**方法**：

为了满足栈的特性，即最后入栈的元素最先出栈，在使用队列实现栈时，应满足队列前端的元素是最后入栈的元素。可以使用两个队列实现栈的操作，其中 `queue` 用于存储栈内的元素，`help` 作为入栈操作的辅助队列。

**入栈操作时**，将元素入队到 `queue` 即可。

**出栈操作时**，也就是需要返回 `queue` 中的队尾元素。首先，先把除了队尾之外的元素全部入队到 `help` （此时，`queue` 中只剩下那个需要出栈的元素），然后再将该元素出队，作为返回值。最后互换 `help` 与 `queue` 即可。

<img src="https://tva1.sinaimg.cn/large/008vxvgGgy1h84z0dmg07j30u00ybn09.jpg" alt="IMG_5917" style="zoom:33%;" />

可见文件 `TwoQueueImplementStack.java` 具体实现。
