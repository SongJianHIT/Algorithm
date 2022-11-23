# 116. 填充每个节点的下一个右侧节点指针

## 题目描述

给定一个 **完美二叉树** ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 `NULL`。

初始状态下，所有 next 指针都被设置为 `NULL`。 

**示例 1：**

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h8err1dcgaj30te0a10tg.jpg)

```
输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
```

**示例 2:**

```
输入：root = []
输出：[]
```

**提示：**

- 树中节点的数量在 `[0, 212 - 1]` 范围内
- `-1000 <= node.val <= 1000`

**进阶：**

- 你只能使用常量级额外空间。
- 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

## 思路与分析

看题目可以发现，`next` 指针只是只向同一层的右侧节点，所以本题的关键就是 **层序遍历**！

接下来的两个方法，都是使用不同方式去 **识别不同层**！

### 方法一：使用两个队列

`queue1` 用来遍历当前层的节点，并添加对应的孩子结点到 `queue2` 。

`queue2` 保存的全部结点就是一层内容，只需要对每个结点添加 `next` 指针即可。

```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.offer(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                // 把 q1 内的孩子全放入 q2
                Node tmp = queue1.poll();
                System.out.println(tmp.val);
                if (tmp.left != null) {
                    queue2.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue2.offer(tmp.right);
                }
            }
            // 此时 q2 放了同一层的节点
            while (!queue2.isEmpty()) {
                Node tmp = queue2.poll();
                if (queue2.isEmpty()) {
                    tmp.next = null;
                } else {
                    tmp.next = queue2.peek();
                }
                queue1.offer(tmp);
            } 
        }
        return root;
    }
}
```

空间复杂度： $O(N)$ ，时间复杂度 $O(N)$ 。

### 方法二：直接使用 size 方法

不需要使用额外的队列进行来回倒腾了，只要 **记录每次添加下一层元素前的队列大小，就是该层的结点数**。

```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add(root);
        
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            
            // 记录当前队列大小
            int size = queue.size();
            
            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {
                
                // 从队首取出元素
                Node node = queue.poll();
                
                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                
                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        
        // 返回根节点
        return root;
    }
}
```

时间复杂度： $O(N)$ ，每个元素只会被访问一次。空间复杂度： $O(N)$ 。







