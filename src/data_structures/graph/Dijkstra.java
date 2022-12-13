/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Dijkstra
 */
package data_structures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Dijkstra
 * @description 单源最短路算法
 * @author SongJian
 * @date 2022/12/13 17:18
 * @version
 */
public class Dijkstra {

    /**
     * @title dijkstra1
     * @author SongJian
     * @param: from
     * @updateTime 2022/12/13 17:29
     * @return: java.util.HashMap<data_structures.graph.Node,java.lang.Integer>
     * @throws
     * @description 给定开始节点，返回从开始节点出发的到任意节点的距离表
     */

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 自己到自己的距离为0
        distanceMap.put(from, 0);
        // 被选择过作为跳转节点的点
        HashSet<Node> selectedNode = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        // minNode是跳转点
        while (minNode != null) {
            // 原始点 -》 跳转点 的最小距离
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    // 如果当前节点不在距离表中，说明此时距离是正无穷
                    // 距离设置为原始点到跳转点的距离 + 边的权重
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果当前节点在距离表中
                    // 比较旧距离和当前通过跳转节点的距离来更新距离表
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    /**
     * @title getMinDistanceAndUnselectedNode
     * @author SongJian
     * @param: distanceMap
     * @param: toCacheNode
     * @updateTime 2022/12/13 17:31
     * @return: data_structures.graph.Node
     * @throws
     * @description 返回没有被当作跳转节点，且是在距离表中最小距离的节点
     *              该实现比较暴力，因为每次都需要遍历所有的点，有优化的空间
     */
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> toCacheNode) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!toCacheNode.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
}
 
