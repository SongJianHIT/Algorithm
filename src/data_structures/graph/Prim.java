/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Prim
 */
package data_structures.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim
 * @description 最小生成树 Prim 算法
 * @author SongJian
 * @date 2022/12/13 16:42
 * @version
 */
public class Prim {

    /**
     * 优先选权值较小的边
     */
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * @title primMST
     * @author SongJian
     * @param: graph
     * @updateTime 2022/12/13 16:44
     * @return: java.util.Set<data_structures.graph.Edge>
     * @throws
     * @description Prim 最小生成树算法
     */
    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 记录被解锁出的点
        Set<Node> set = new HashSet<>();
        // 确定选择的边，即返回结果
        Set<Edge> res = new HashSet<>();

        // 这个for循环是为了防森林
        for (Node node : graph.nodes.values()) {
            // node 是开始点
            if (!set.contains(node)) {
                // 解锁节点
                set.add(node);
                // 解锁直接相连的边
                for (Edge edge : node.edges) {
                    priorityQueue.offer(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    // 所有解锁的边中，权值最小的边
                    Edge edge = priorityQueue.poll();
                    // 判断边连接的点是不是已经被解锁了
                    Node toNode = edge.to;
                    // 如果没有被解锁
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        res.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.offer(nextEdge);
                        }
                    }
                }
            }
        }
        return res;
    }
}
 
