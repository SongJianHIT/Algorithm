/**
 * @projectName Algorithm
 * @package Tree.BinaryTree
 * @className Tree.BinaryTree.IsSubStructure
 */
package Tree.BinaryTree;

import javax.swing.tree.TreeNode;

/**
 * IsSubStructure
 *
 * @author SongJian
 * @description
 * @date 2022/12/3 09:33
 */
public class IsSubStructure {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));

    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
 
