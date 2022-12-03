/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.EncodeNaryTreeToBinaryTree
 */
package data_structures.binarytree;

import java.util.List;

/**
 * EncodeNaryTreeToBinaryTree
 * @description 多叉树编码为唯一的二叉树
 * @author SongJian
 * @date 2022/12/3 15:25
 * @version
 */
public class EncodeNaryTreeToBinaryTree {
    /**
     * 多叉树节点
     */
    public static class Node {
        public int val;
        // 多叉树 多个孩子 使用列表表示
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    /**
     * 二叉树节点类
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {

    }


}
 
