/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Graph
 */
package data_structures.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Graph
 * @description 图类
 * @author SongJian
 * @date 2022/12/13 11:03
 * @version
 */
public class Graph {
    // 点集
    public HashMap<Integer, Node> nodes;
    // 边集
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
 
