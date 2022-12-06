/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.MaxSubBST
 */
package data_structures.binarytree;

import java.util.ArrayList;

/**
 * MaxSubBST
 * @description 最大二叉搜索子树
 * @author SongJian
 * @date 2022/12/6 10:19
 * @version
 */
public class MaxSubBST {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static int maxSubBST(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSize;
    }

    static class Info {
        public int max;
        public int min;
        public int allSize;
        public int maxBSTSize;

        public Info(int max, int min, int allSize, int maxBSTSize) {
            this.max = max;
            this.min = min;
            this.allSize = allSize;
            this.maxBSTSize = maxBSTSize;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.val;
        int min = x.val;
        int allSize = 1;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allSize += rightInfo.allSize;
        }
        int p1 = -1, p2 = -1, p3 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSize;
        }
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSize;
        }
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
            boolean rightMinLargeX = rightInfo == null ? true : (rightInfo.min > x.val);
            if (leftMaxLessX && rightMinLargeX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        int maxBSTSize = Math.max(p1, Math.max(p2, p3));
        return new Info(max, min, allSize, maxBSTSize);
    }

    // 为了验证
    // 对数器方法
    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBST(head) != right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}
 
