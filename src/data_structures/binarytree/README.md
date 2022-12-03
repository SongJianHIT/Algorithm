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





























