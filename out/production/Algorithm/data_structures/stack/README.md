# 数据结构：栈

## 1 基本结构介绍

## 2 常见题型

### 2.1 特殊栈的实现

> LeetCode：https://leetcode.cn/problems/min-stack-lcci/description/

实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能：

- `pop` 、`push` 、`getMin` 操作的时间复杂度都是 $O(1)$
- 设计的栈类型可以使用现成的栈结构

```java
class MinStack {

    /** initialize your data structure here. */
    private List<Integer> stack;
    private List<Integer> minStack;
    private int index;
    public MinStack() {
        this.stack = new ArrayList<>();
        this.minStack = new ArrayList<>();
        index = -1;
    }
    
    public void push(int x) {
        stack.add(x);
        if (minStack.size() == 0 || minStack.get(index) >= x) {
            minStack.add(x);
        } else {
            minStack.add(minStack.get(index));
        }
        index++;
    }
    
    public void pop() {
        if (!stack.isEmpty()){
            stack.remove(index);
            minStack.remove(index);
            index--;
        }
    }
    
    public int top() {
        return stack.get(index);
    }
    
    public int getMin() {
        return minStack.get(index);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

### 2.2 使用栈实现队列

> LeetCode：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

> **本题的作用**：
>
> - 如何用栈实现图的广度优先搜索？
> - 如何用队列实现图的深度优先搜索？
>
> 都是先用栈实现队列，或队列实现栈。

针对本题，设计两个栈，一个 `push` 栈，另一个 `pop` 栈。

- 当有数据需要入队时，直接压入 `push` 栈
- 当有数据需要出队时，先把 `push` 栈内的元素倒入 `pop` 栈中，然后出栈即可。

只需要保证两点：

- 当把 `push` 栈内的元素倒入 `pop` 栈时，必须完完整整全部倒出去！
- 当有数据出队时，需要保证 `push` 栈中没有任何元素！

**只要保证以上两点，不论怎么实现都是正确的。**

```java
class CQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;
    public CQueue() {
        stackPush = new Stack<Integer>();
        stackPop = new Stack<Integer>();
    }
    
    public void appendTail(int value) {
        stackPush.push(value);
    }
    
    public int deleteHead() {
        if (stackPush.isEmpty()) return -1;
        while (!stackPush.isEmpty()) {
            stackPop.push(stackPush.pop());
        }
        int res = stackPop.pop();
        while (!stackPop.isEmpty()) {
            stackPush.push(stackPop.pop());
        }
        return res;
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```















