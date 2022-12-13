/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.BFS
 */
package data_structures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 * @description 宽度优先遍历
 * @author SongJian
 * @date 2022/12/13 11:46
 * @version
 */
public class BFS {
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                // 如果邻居节点不在 set 中，才入队
                // 否则不入队
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
 
