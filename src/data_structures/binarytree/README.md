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

