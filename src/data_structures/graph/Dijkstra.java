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
 *
 * @author SongJian
 * @description 单源最短路算法
 * @date 2022/12/13 17:18
 */
public class Dijkstra {

    /**
     * @throws
     * @title dijkstra1
     * @author SongJian
     * @param: from
     * @updateTime 2022/12/13 17:29
     * @return: java.util.HashMap<data_structures.graph.Node, java.lang.Integer>
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
     * @throws
     * @title getMinDistanceAndUnselectedNode
     * @author SongJian
     * @param: distanceMap
     * @param: toCacheNode
     * @updateTime 2022/12/13 17:31
     * @return: data_structures.graph.Node
     * @description 返回没有被当作跳转节点，且是在距离表中最小距离的节点
     * 该实现比较暴力，因为每次都需要遍历所有的点，有优化的空间
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

    /**
     * ================================================================================================================
     * 方法二：使用加强堆优化
     */

    /**
     * 记录类
     */
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 加强堆
     */
    public static class NodeHeap {
        // 堆
        private Node[] nodes;
        // 反向索引表，记录每个点在堆上什么位置
        // 设计：index 设置为 -1 时，说明该元素进来过
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            size = 0;
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * @title addOrUpdateOrIgnore
         * @author SongJian
         * @param: node
         * @param: distance
         * @updateTime 2022/12/14 10:31
         * @throws
         * @description 对于一个节点 node，现在有一个新的距离 distance，可能会更新距离表
         */
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // update
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                // 因为距离只能变小或者不变，因此一定是向上（堆顶）调整！
                insertHeapify(node, heapIndexMap.get(node));
            }
            // add
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
            // ignore
        }

        /**
         * @title isEntered
         * @author SongJian
         * @param: node
         * @updateTime 2022/12/14 10:20
         * @return: boolean
         * @throws
         * @description node 节点是否进入过堆
         */
        private boolean isEntered(Node node) {
            // 有记录就一定进来过
            // 即便"出去"，也是将其index设置为 -1，它仍在反向索引表中
            return heapIndexMap.containsKey(node);
        }

        /**
         * @title inHeap
         * @author SongJian 
         * @param: node
         * @updateTime 2022/12/14 10:21 
         * @return: boolean
         * @throws
         * @description node 节点当前是否在堆上
         *              需要满足：node 节点进来过堆，并且它的 index 不为 -1
         */
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        /**
         * @title swap
         * @author SongJian
         * @param: index1
         * @param: index2
         * @updateTime 2022/12/14 10:42
         * @throws
         * @description 交换元素。在换的时候，反向索引表和堆上都要同步换！
         */

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        /**
         * @title pop
         * @author SongJian
         * @updateTime 2022/12/14 10:40
         * @return: data_structures.graph.Dijkstra.NodeRecord
         * @throws
         * @description 堆顶弹出元素
         */
        public NodeRecord pop() {
            // ndoes[0] 堆顶
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            // 交换堆顶和最后一个值
            swap(0, size - 1);
            // 反向索引表的index改成 -1
            heapIndexMap.put(nodes[size - 1], -1);
            // 距离表删除
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }
    }

    /**
     * @throws
     * @title dijstra2
     * @author SongJian
     * @param: head
     * @param: size
     * @updateTime 2022/12/14 10:05
     * @return: java.util.HashMap<data_structures.graph.Node, java.lang.Integer>
     * @description 从 head 出发，所有 head 能够到达的节点，生成到达每个节点的最小路径记录并返回
     */
    public static HashMap<Node, Integer> dijstra2(Node head, int size) {
        // 构建加强堆
        NodeHeap nodeHeap = new NodeHeap(size);
        // add Or Update Or Ignore
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        // 创建距离表
        HashMap<Node, Integer> result = new HashMap<>();
        // 堆中为空就退出循环
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

}
 
