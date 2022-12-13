/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.DFS
 */
package data_structures.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * DFS
 * @description 图的深度优先遍历
 * @author SongJian
 * @date 2022/12/13 11:53
 * @version
 */
public class DFS {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        // 定义栈，迭代实现
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);

        // 入栈就访问
        System.out.println(start.value);

        // 这个栈，就是目前遍历的整条路径！
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                // 没有在集合中注册过才执行
                if (!set.contains(next)) {
                    // 弹出节点重新入栈
                    stack.push(cur);
                    // 邻居入栈
                    stack.push(next);
                    set.add(next);
                    // 访问节点
                    System.out.println(next.value);
                    // 跳出邻居节点的遍历，回到上一层
                    break;
                }
            }
        }
    }
}
 
