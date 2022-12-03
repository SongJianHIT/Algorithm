/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.LevelTraversalBT
 */
package data_structures.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LevelTraversalBT
 * @description 二叉树层序遍历
 * @author SongJian
 * @date 2022/12/3 10:23
 * @version
 */
public class LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

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

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTravel(head);
        System.out.println("========");
    }
}
 
