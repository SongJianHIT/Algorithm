# 二叉树

## 基本介绍

### 基本结构类

二叉树的基本结构：

```java
public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
}
```

### 二叉树遍历

- 先序遍历：头->左->右
- 中序遍历：左->头->右
- 后序遍历：左->右->头
- 层序遍历

具体实现，**递归**：

```java
	// 先序
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		pre(head.left);
		pre(head.right);
	}
	// 中序
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.println(head.value);
		in(head.right);
	}
	// 后序
	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}
```

层序遍历：队列实现：

```java
		public static void levelTravel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            // 访问
            System.out.println(tmp.value);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
    }
```

### 树形DP

树形dp能够解决很多问题，并且可以保证时间复杂度为 $O(N)$ ，其中 $N$ 为节点数量。

> 本质上是后续遍历，在递归序上每个节点访问了三次。

#### 思路

- 假设以 `x` 节点为头，假设可以向 `x` 左树和 `x` 右树要任何信息
- 在上一步的假设下，讨论以 `x` 为头节点的树，得到答案的可能性（最重要）
- 列出所有可能性后，确定到底需要向左树和右树要什么样的信息
- 把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息 `S`
- 递归函数都必须返回 `S` ，每棵子树都按照此要求
- 写代码



## 经典题型

### 先、中、后序的非递归实现

#### 先序遍历非递归实现

先序遍历的非递归比较容易， **具体步骤：**

- 创建栈，并将头节点入栈
- 若栈不为空：
  - 出栈，访问
  - 右孩子若存在，则入栈
  - 左孩子若存在，则入栈

> 这里需要注意一点，就是左右孩子的入栈顺序，**先序遍历一定是先右再左入栈。因为这样可以保证出栈顺序是左->右。**

```java
/**
* 先序遍历：栈实现
*
* @param head
*/
public static void pre(Node head) {
  System.out.println("先序遍历：");
  if (head == null) {
    Stack<Node> stack = new Stack<>();
    stack.add(head);
    while (!stack.isEmpty()) {
      head = stack.pop();
      System.out.println(head);
      // 入栈顺序：先右后左
      // 出栈顺序：先左后右
      if (head.right != null) {
        stack.push(head.right);
      }
      if (head.left != null) {
        stack.push(head.left);}
    }
  }
  System.out.println();
}
```

#### 后序遍历非递归实现

上面我们已经实现了先序遍历的非递归版本，即使用一个栈来实现。

先序遍历的顺序是 `头左右` 。如果我们交换一下入栈顺序，即可调整成 `头右左` 。最后，我们先把这个顺序放入栈中，最后倒出来就可以得到后序 `左右头` ！

**具体步骤：**

- 创建栈 `A` 和 栈 `B` ，并将头节点入栈 `A`
- 若栈 `A` 不为空：
  - 栈 `A` 元素出栈，进入栈 `B` 
  - 出栈元素的右孩子若存在，则入栈 `A`
  - 出栈元素的左孩子若存在，则入栈 `A`
- 倾倒栈 `B` 

```java
/**
 * 先按照先序遍历（头左右）的思路，改出（头右左）的顺序，
 * 然后将访问元素压入另一个栈。最后出栈的时候，即得到后序（左右头）
 *
 * @param head
 */
public static void post(Node head) {
  System.out.println("后续遍历；");
  if (head != null) {
    Stack<Node> stack = new Stack<>();
    Stack<Node> post = new Stack<>();
    stack.push(head);
    while (!stack.isEmpty()) {
      head = stack.pop();
      post.push(head);
      if (head.left != null) {
        post.push(head.left);
      }
      if (head.right != null) {
        post.push(head.right);
      }
    }
    // post出栈便是后续遍历的顺序
    while (!post.isEmpty()) {
      System.out.println(post.pop().value);
    }
  }
  System.out.println();
}

    
```

#### 中序遍历非递归实现

相比于前面的先序遍历和后续遍历，中序遍历的改写稍微复杂一点。

**具体步骤：**

![IMG_9360177FFBF4-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8qfvqrpzhj318k0u0tgr.jpg)

```java
/**
* 中序遍历非递归版本
* @param cur
*/
public static void in(Node cur) {
  System.out.println("中序遍历：");
  if (cur != null) {
    Stack<Node> stack = new Stack<>();
    while (!stack.isEmpty() || cur != null) {
      // 整条左边界入栈
      if (cur != null) {
        stack.push(cur);
        cur = cur.left;
      } else {
        cur = stack.pop();
        System.out.println(cur);
        cur = cur.right;
      }
    }
  }
  System.out.println();
}
```

### 二叉树的序列化与反序列化

序列化：将树存储以一个 **唯一的字符串** 来表示

反序列化：将一个字符串转化成一棵树

> 树和字符串是一个一一对应的关系！
>
> 注意：**无法通过中序遍历进行序列化！** 

#### 先序序列化

```java
public static Queue<String> preSerial(Node head) {
  // 使用队列，是允许放空的
  Queue<String> ans = new LinkedList<>();
  // 在先序的过程中，将序列化结果添加入队列
  pres(head, ans);
  return ans;
}

public static void pres(Node head, Queue<String> ans) {
  if (head == null) {
    ans.add(null);
  } else {
    // head
    ans.add(String.valueOf(head.value));
    // left
    pres(head.left, ans);
    // right
    pres(head.right, ans);
  }
}
```

#### 先序反序列化

```java
public static Node buildByPreQueue(Queue<String> preList) {
  if (preList == null || preList.size() == 0) {
    return null;
  }
  return preb(preList);
}


public static Node preb(Queue<String> preList) {
  String value = preList.poll();
  if (value == null) {
    return null;
  }
  // 建立节点 head
  Node head = new Node(Integer.valueOf(value));
  // left
  head.left = preb(preList);
  // right
  head.right = preb(preList);
  return head;
}
```

#### 中序序列化

```java
public static Queue<String> levelSerial(Node head) {
  Queue<String> ans = new LinkedList<>();
  if (head == null) {
    // 空树
    ans.add(null);
  } else {
    // 用来层序遍历的队列
    // 每个元素进入 queue 时，就进行序列化
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    ans.add(String.valueOf(head.value));
    while (!queue.isEmpty()) {
      head = queue.poll();
      if (head.left != null) {
        ans.add(String.valueOf(head.left.value));
        queue.add(head.left);
      } else {
        // 保持序列化，别遗漏了！
        ans.add(null);
      }
      if (head.right != null) {
        ans.add(String.valueOf(head.right.value));
        queue.add(head.right);
      } else {
        ans.add(null);
      }
    }
  }
  return ans;
}
```

#### 中序反序列化

```java
public static Node buildByLevelQueue(Queue<String> levelList) {
  if (levelList == null || levelList.size() == 0) {
    return null;
  }
  // 使用队首元素构建头
  Node head = generateNode(levelList.poll());
  // 层序遍历辅助队列
  Queue<Node> queue = new LinkedList<>();
  if (head != null) {
    queue.add(head);
  }
  Node node = null;
  while (!queue.isEmpty()) {
    node = queue.poll();
    // 反序列化
    node.left = generateNode(levelList.poll());
    node.right = generateNode(levelList.poll());
    if (node.left != null) {
      queue.add(node.left);
    }
    if (node.right != null) {
      queue.add(node.right);
    }
  }
  return head;
}
```

### 多叉树 为二叉树

> LeetCode：https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree/

设计一个算法，可以将 N 叉树编码为二叉树，并能将该二叉树解码为原 N 叉树。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。类似地，一个二叉树是指每个节点都有不超过 2 个孩子节点的有根树。你的编码 / 解码的算法的实现没有限制，你只需要保证一个 N 叉树可以编码为二叉树且该二叉树可以解码回原始 N 叉树即可。

例如，你可以将下面的 `3-叉` 树以该种方式编码：

![img](https://img-blog.csdnimg.cn/img_convert/8c9e91a42dcb9286b5ae5765f3d90c3b.png)

注意，上面的方法仅仅是一个例子，可能可行也可能不可行。你没有必要遵循这种形式转化，你可以自己创造和实现不同的方法。

**注意：** 

1. `N` 的范围在 `[1, 1000]`
2. 不要使用类成员 / 全局变量 / 静态变量来存储状态。你的编码和解码算法应是无状态的。

#### 方法

关键点：**对于树中任意一个节点，它的所有的孩子都在其左树右边界上** 。

![IMG_9C8C941BFBFD-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8qnghptgdj31iu0phq6r.jpg)

### 找到二叉树中最宽的一层

二叉树的宽度是指各层结点数的最大值。

下图中第一层结点数为 1，第二层为 2，第三层为 3，第四层为 2，所以二叉树的宽度为 3。

![image-20221203160224937](https://tva1.sinaimg.cn/large/008vxvgGgy1h8qokwv3rjj30kq06s3yg.jpg)

#### 层序遍历+标记

在层序遍历的基础上，增加几个变量：

-  `curEnd` 当前层结束节点
-  `nextEnd` 下一层结束节点
-  `curCount` 当前层节点计数
-  `max` 最宽层的节点数量 

### 后继节点

二叉树结构如下定义：

```java
public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
}
```

给定二叉树的某个节点，返回该节点在中序遍历下的后继节点。

**具体步骤：**

- 如果节点 `x` 存在右孩子 `x->right` ，则 `x` 的后继节点一定是 **右树中的最左节点** 。

- 如果节点 `x` 不存在右孩子 `x->right` ：

  - 如果节点 `x` 是它父亲 `x->parent` 的右孩子 `x->parent>right` ，则往上找。直到某个节点是它父亲的左孩子。
  - 此时，`x` 的后继节点就是该节点的父亲，即 `x->parent->left = x` 的 `parent` 。
  - 如果找不到该父节点，则该节点 `x` 无后继。

  > 原因：因为此时 `x` 是返回的 `parent` 的左子树的最右节点，访问完 `x` 后就访问 `parent` 了。

### 判断二叉树是否是完全二叉树

给你头节点，判断该二叉树是否是完全二叉树，是则返回 `true` ，否则返回 `false` 。

**使用层序遍历，讨论：**

- 如果某个节点 `x` ，如果它的 **右孩子不为空，且左孩子为空** `x.right != null && x.left == null` 则一定不是完全二叉树。
- 判断完上条后，当第一次遇到左右孩子不双全的时候，剩下的节点必定是叶节点。

### 判断二叉树是否是平衡二叉树

给定一棵二叉树的头节点 `head`，返回这棵二叉树是否是平衡二叉树。

#### 方法：树形dp

思路：对于某个以 `x` 为根节点的子树，如果想要是平衡二叉树：

1. `x` 的左孩子是平衡二叉树
2. `x` 的右孩子是平衡叉树
3. 自己也满足平衡条件： `|x左孩子高度 - x右孩子高度| < 2`

因此，`x` 需要什么信息？

- 左子树：是否平？左子树高度
- 右子树：是否平？右子树高度

### 判断二叉树是否是搜索二叉树

给定一棵二叉树的头节点 `head`，返回这棵二叉树是否是搜索二叉树。

#### 方法：树形dp

思路：对于某个以 `x` 为根节点的子树，如果想要是搜索二叉树：

1. `x` 的左子树是搜索二叉树
2. `x` 的右子树是搜索二叉树
3. `x` 的左子树的最大值小于 `x`
4. `x` 的右子树的最小值大于 `x`

### 二叉树的最大距离

给定一棵二叉树的头节点 `head` ，任何两个节点之间都存在距离，返回整棵二叉树的最大距离。

#### 方法：树形dp

思路：对于某个以 `x` 为根节点的子树，它的最大距离：

1. （ `x` 无关） `x` 左树上的最大距离

2. （ `x` 无关） `x` 右树上的最大距离

3. （ `x` 有关）`x` 左树上离 `x` 最远，走到 `x` 右树离 `x` 最远 + 1

   > 即 `左树最大高度 + 右树最大高度 + 1`

满二叉树满足：`2^{节点数量} - 1 = 树高`

#### 方法：树形dp

记录每棵子树的节点数量与树高，最后判断即可。

### 最大子二叉搜索树

给出二叉树头节点 `head` ，求以 `head` 为根节点的最大的二叉搜索子树的大小。即 `head` 可能不是搜索二叉树，而他的某个子树可能是搜索二叉树。

#### 方法：树形dp

对于某一个根节点 `x` ，我们需要记录：

1. 左子树是否是二叉搜索树
2. 右子树是否是二叉搜索树
3. 左子树中的最大值
4. 右子树中的最小值
5. 左子树最大搜索二叉树大小
6. 右子树最大搜索二叉树大小
7. 左子树大小
8. 右子树大小

### 最低公共祖先

给定一棵二叉树的头节点 `head` ，和另外两个节点 `a` 和 `b` ，返回 `a` 和 `b` 的最低公共祖先。

#### 方法：树形dp

对于某一个子树的根节点 `x` ：

1. 如果 `x` 不是最低的汇聚点（不是答案）
   - 左树上有答案，`x` 不是答案
   - 右树上有答案，`x` 不是答案
   - `x` 的整棵树上， `a` 和 `b` 不全
2. 如果 `x` 是最低汇聚点（是答案）
   - 左树发现一个，右树发现另一个，在 `x` 汇聚
   - `x` 本身就是节点之中的一个，另一个在左右子树中发现

需要的信息：

- 树上发现了 `a` ？
- 树上发现了 `b` ？
- 树上发现了最低公共祖先答案？









