/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.UnRecursiveTraversalBT
 */
package data_structures.binarytree;

import java.util.Stack;

/**
 * UnRecursiveTraversalBT
 *
 * @author SongJian
 * @description 遍历二叉树非递归版本
 * @date 2022/12/2 19:27
 */
public class UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

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
}
 
