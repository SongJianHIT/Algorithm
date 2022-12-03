/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.Serialize
 */
package data_structures.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialize
 * @description 二叉树的序列化与反序列化
 * @author SongJian
 * @date 2022/12/3 11:10
 * @version
 */
public class SerializeAndReconstructTree {
    /*
     * 二叉树可以通过 先序、后序 或者 按层遍历 的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * @title preSerial
     * @author SongJian
     * @param: head
     * @updateTime 2022/12/3 11:13
     * @throws
     * @description 给定头节点，使用先序遍历的方式对树进行序列化
     */
    public static Queue<String> preSerial(Node head) {
        // 使用队列，是允许放空的
        Queue<String> ans = new LinkedList<>();
        // 在先序的过程中，将序列化结果添加入队列
        pres(head, ans);
        return ans;
    }

    /**
     * @title pres
     * @author SongJian
     * @param: head
     * @param: ans
     * @updateTime 2022/12/3 11:18
     * @throws
     * @description 先序遍历递归，并将当前遍历的节点序列化
     */
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

    /**
     * @title buildByPreQueue
     * @author SongJian
     * @param: preList
     * @updateTime 2022/12/3 11:22
     * @return: data_structures.binarytree.SerializeAndReconstructTree.Node
     * @throws
     * @description 先序遍历的反序列化
     */
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            return null;
        }
        return preb(preList);
    }

    /**
     * @title preb
     * @author SongJian
     * @param: preList
     * @updateTime 2022/12/3 11:23
     * @return: data_structures.binarytree.SerializeAndReconstructTree.Node
     * @throws
     * @description 先序遍历的反序列化
     */
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
}
 
