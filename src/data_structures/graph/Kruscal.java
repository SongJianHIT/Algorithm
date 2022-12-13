/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Kruscal
 */
package data_structures.graph;

import java.util.*;

/**
 * Kruscal
 * @description 克鲁斯卡尔算法 最小生成树
 * @author SongJian
 * @date 2022/12/13 15:47
 * @version
 */
public class Kruscal {

    /**
     * 并查集
     */
    public static class UnionFind{

        // key 某一个节点， value key往上的节点
        private HashMap<Node, Node> parent;
        // key 集合的代表节点， value 集合大小
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            parent.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 找到节点 n 的代表节点
         * @param n
         * @return
         */
        public Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (n != parent.get(n)) {
                path.push(n);
                n = parent.get(n);
            }
            while (!path.isEmpty()) {
                parent.put(path.pop(), n);
            }
            return n;
        }

        /**
         * 判断 a 和 b 是否在同一个集合
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        /**
         * 合并节点
         * @param a
         * @param b
         */
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node f1 = findFather(a);
            Node f2 = findFather(b);
            if (f1 != f2) {
                int s1 = sizeMap.get(f1);
                int s2 = sizeMap.get(f2);
                if (s1 <= s2) {
                    parent.put(f1, f2);
                    sizeMap.put(f2, s1 + s2);
                    sizeMap.remove(f1);
                } else {
                    parent.put(f2, f1);
                    sizeMap.put(f1, s1 + s2);
                    sizeMap.remove(f2);
                }
            }
        }
    }

    /**
     * 权重较小的边先选
     */
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * @title kruscalMST
     * @author SongJian
     * @param: graph
     * @updateTime 2022/12/13 16:24
     * @return: java.util.Set<data_structures.graph.Edge>
     * @throws
     * @description 克鲁斯卡尔算法，最小生成树
     */

    public static Set<Edge> kruscalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边依次弹出，使用小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.offer(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            // 判断是否构成环
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return res;
    }

}
 
