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

非递归稍微难一点，都是基于栈实现的：

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
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

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

