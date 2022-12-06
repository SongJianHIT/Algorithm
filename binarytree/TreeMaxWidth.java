/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.TreeMaxWidth
 */
package data_structures.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TreeMaxWidth
 * @description 二叉树的最大宽度
 * @author SongJian
 * @date 2022/12/3 16:19
 * @version
 */
public class TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * @title maxWidth
     * @author SongJian
     * @param: head
     * @updateTime 2022/12/3 16:21
     * @return: int
     * @throws
     * @description 不使用容器，计算二叉树最大宽度
     */
    public static int maxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        // 当前层结尾节点
        Node curEnd = head;
        // 下一层结尾节点
        Node nextEnd = null;
        int max = 0;
        // 当前层节点个数
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                // 设置下层结尾
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                // 设置下层节点
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                // 当前节点为当前层最后节点
                // 统计当前层节点数量，更新max
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
 
