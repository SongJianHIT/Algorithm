/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Edge
 */
package data_structures.graph;

/**
 * Edge
 * @description 图中的边类
 * @author SongJian
 * @date 2022/12/13 11:02
 * @version
 */
public class Edge {

    // 边上的权重
    public int weight;

    // 边的起始节点
    public Node from;

    // 边的结束节点
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
 
