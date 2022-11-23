/**
 * @projectName Algorithm
 * @package BFS_DFS.LeetCode116
 * @className BFS_DFS.LeetCode116.Connect
 */
package BFS_DFS.LeetCode116;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Connect
 * @description 116. 填充每个节点的下一个右侧节点指针
 * @author SongJian
 * @date 2022/11/23 08:26
 * @version
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class Connect {

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
                // 再把 queue2 中的节点放回 queue1
                queue1.offer(tmp);
            }
        }
        return root;
    }

    public Node connect_2(Node root) {
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
 
