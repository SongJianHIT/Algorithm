/**
 * @projectName Algorithm
 * @package data_structures.binarytree
 * @className data_structures.binarytree.GetSuccessorNode
 */
package data_structures.binarytree;

/**
 * GetSuccessorNode
 * @description 中序遍历中的后继节点
 * @author SongJian
 * @date 2022/12/4 21:12
 * @version
 */
public class GetSuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * @title getSuccessorNode
     * @author SongJian
     * @param: node
     * @updateTime 2022/12/4 21:13
     * @return: data_structures.binarytree.GetSuccessorNode.Node
     * @throws
     * @description 给定节点 node，求 node 下树中中序遍历的后继节点
     *              时间复杂度 O(K)，K 是 node 到其后继节点的路径长度
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        // 如果该节点有右树
        // 则找右树的最左节点
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            // 该节点没有右树
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                // 父节点不为空，并且node是它父结点的右孩子，则上移
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * @title getLeftMost
     * @author SongJian
     * @param: node
     * @updateTime 2022/12/4 21:17
     * @return: data_structures.binarytree.GetSuccessorNode.Node
     * @throws
     * @description 求以 node 为根节点的最左孩子
     */
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
 
