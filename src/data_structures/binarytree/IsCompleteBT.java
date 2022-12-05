/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.IsCompleteBT
 */
package data_structures.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * IsCompleteBT
 * @description 判断是否是完全二叉树
 * @author SongJian
 * @date 2022/12/5 10:23
 * @version
 */
public class IsCompleteBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    
    /**
     * @title isCompleteBT_1
     * @author SongJian 
     * @param: head
     * @updateTime 2022/12/5 10:28 
     * @return: boolean
     * @throws
     * @description 给定头节点，判断以头节点的二叉树是否是完全二叉树
     */
    public boolean isCompleteBT_1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // leaf 判断是否遇到了左右孩子不双全的情况
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null) || (r != null)) || (l == null && r != null)) {
                // 两个失败条件
                // 1、节点有右孩子，无左孩子
                // 2、已经访问过左右孩子非双全的节点，但后续访问的节点仍不是叶子结点
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }
}
 
