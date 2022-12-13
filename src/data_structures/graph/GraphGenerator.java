/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.GraphGenerator
 */
package data_structures.graph;

/**
 * GraphGenerator
 * @description 图类的适配器，将其他表示方法转化为自己的表示方法
 * @author SongJian
 * @date 2022/12/13 11:07
 * @version
 */
public class GraphGenerator {

    /**
     * matrix 是一个 N*3 的矩阵，内容为：[weight，from节点上面的值，to节点上面的值]
     * @param matrix
     * @return
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        // 对每一条边建立节点和边
        for (int i = 0; i < matrix.length; ++i) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            // 如果不存在，
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            // 创建边类
            Edge newEdge = new Edge(weight, fromNode, toNode);

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
 
