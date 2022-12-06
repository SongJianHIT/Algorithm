/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.EncodeNaryTreeToBinaryTree
 */
package data_structures.binarytree;

import java.util.ArrayList;
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
    
    /**
     * @title encode
     * @author SongJian 
     * @param: root
     * @updateTime 2022/12/3 15:30 
     * @return: data_structures.binarytree.EncodeNaryTreeToBinaryTree.TreeNode
     * @throws 
     * @description 将多叉树编码为二叉树
     */
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        // 头节点的所有孩子挂到左树右边界上
        head.left = en(root.children);
        return head;
    }

    /**
     * 编码逻辑
     * @param children
     * @return
     */
    private  TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = new TreeNode(child.val);
            if (head == null) {
                // 第一个孩子会进入这个if逻辑
                head = tNode;
            } else {
                // 其他孩子挂到cur的右边
                cur.right = tNode;
            }
            // cur指向当前节点
            cur = tNode;
            // 递归挂上孩子，深度优先遍历
            cur.left = en(child.children);
        }
        return head;
    }

    /**
     * @title decode
     * @author SongJian
     * @param: root
     * @updateTime 2022/12/3 15:43
     * @return: data_structures.binarytree.EncodeNaryTreeToBinaryTree.Node
     * @throws
     * @description 将二叉树解码回多叉树
     */
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    /**
     * 解码逻辑
     * @param root
     * @return
     */
    private List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            // 深度优先解码
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }
}
 
