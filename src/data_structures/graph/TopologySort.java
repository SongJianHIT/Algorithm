/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.TopologySort
 */
package data_structures.graph;

import java.util.*;

/**
 * TopologySort
 * @description 拓扑排序
 * @author SongJian
 * @date 2022/12/13 14:06
 * @version
 */
public class TopologySort {

    /**
     * @title sortedTopology
     * @author SongJian
     * @param: graph
     * @updateTime 2022/12/13 14:06
     * @return: java.util.List<data_structures.graph.Node>
     * @throws
     * @description 给定一个 有向无环 图，返回其拓扑排序
     */
    public static List<Node> sortedTopology(Graph graph) {
        // key 某个节点， value 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 剩余入度为 0 的节点，入队
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 遍历图中所有的点集
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                // 节点的入度 - 1
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
 
