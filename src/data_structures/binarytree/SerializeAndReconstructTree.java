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

    /**
     * @title levelSerial
     * @author SongJian
     * @param: head
     * @updateTime 2022/12/3 14:39
     * @return: java.util.Queue<java.lang.String>
     * @throws
     * @description 层序方式序列化
     */
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


    /**
     * @title buildByLevelQueue
     * @author SongJian
     * @param: levelList
     * @updateTime 2022/12/3 14:50
     * @return: data_structures.binarytree.SerializeAndReconstructTree.Node
     * @throws
     * @description 层序方式反序列化，返回树头节点
     */
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

    /**
     * @title generateNode
     * @author SongJian
     * @param: val
     * @updateTime 2022/12/3 14:52
     * @return: data_structures.binarytree.SerializeAndReconstructTree.Node
     * @throws
     * @description 根据字符串生成二叉树节点
     */
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
}
 
