/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.LowestAncestor
 */
package data_structures.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * LowestAncestor
 * @description 最低公共祖先
 * @author SongJian
 * @date 2022/12/7 09:46
 * @version
 */
public class LowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean a, boolean b, Node ans) {
            findA = a;
            findB = b;
            this.ans = ans;
        }
    }

    public static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);

        boolean findA = (x == a) || (leftInfo.findA) || (rightInfo.findA);
        boolean findB = (x == b) || (leftInfo.findB) || (rightInfo.findB);

        Node ans = null;
        // 左树或右树发现答案
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            // 左右树都没发现答案
            // 当前节点既发现了a又发现了b
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    public static Node lowestAncestor(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }

    /**
     * ============================================================================================
     */

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    /**
     * ============================================================================================
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
 
