/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.IsBalancedBT
 */
package data_structures.binarytree;

/**
 * IsBalancedBT
 * @description 判断是否是平衡二叉树
 * @author SongJian
 * @date 2022/12/5 10:41
 * @version
 */
public class IsBalancedBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 信息结构体
     */
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 设置每个节点判读后返回什么信息
     * @param x
     * @return
     */
    public static Info process(Node x) {
        if (x == null) {
            // 如果是空树，则是平衡二叉树，且高度为0
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 当前节点的高度，左右孩子的最大高度 + 1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            // 如果左右子树有一个不平，则 x 树肯定也不平
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            // 如果 x 的左右子树高度差大于 1，则 x 树肯定也不平
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

    /**
     * @title isBalancedBT
     * @author SongJian
     * @param: x
     * @updateTime 2022/12/5 11:00
     * @return: boolean
     * @throws
     * @description 判断 x 为根节点的树是否是平衡二叉树
     */

    public static boolean isBalancedBT(Node x) {
        return process(x).isBalanced;
    }

    /**
     * ==================================================================================
     */

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * ==================================================================================
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalancedBT(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
 
