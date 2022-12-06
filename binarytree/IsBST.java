/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.IsBST
 */
package data_structures.binarytree;

import java.util.ArrayList;

/**
 * IsBST
 * @description 判断二叉树是否是搜索二叉树
 * @author SongJian
 * @date 2022/12/5 11:05
 * @version
 */
public class IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 信息节点类
     */
    public static class Info {
        public boolean isBST;
        public int maxValue;
        public int minValue;

        public Info(boolean isBST, int maxValue, int minValue) {
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }
    
    /**
     * @title process
     * @author SongJian 
     * @param: head
     * @updateTime 2022/12/5 11:13 
     * @return: data_structures.binarytree.IsBST.Info
     * @throws
     * @description 树形dp过程
     */
    public static Info process(Node x) {
        if (x == null) {
            return null;
        }

        // 左右孩子的信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 该节点信息
        int max = x.value;
        int min = x.value;
        boolean isBST = true;

        // 处理逻辑
        // 大小值处理
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.maxValue);
            min = Math.min(min, leftInfo.minValue);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.maxValue);
            min = Math.min(min, rightInfo.minValue);
        }
        // 是否是搜索树的判断
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.maxValue >= x.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.minValue <= x.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static boolean isBST2(Node head) {
        if (head == null) return true;
        return process(head).isBST;
    }

    /**
     * ===========================================================================
     */
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    /**
     * ===========================================================================
     */

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
 
