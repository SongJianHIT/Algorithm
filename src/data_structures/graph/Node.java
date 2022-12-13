/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.Node
 */
package data_structures.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Node
 * @description 图节点类
 * @author SongJian
 * @date 2022/12/13 10:58
 * @version
 */
public class Node {

    // 值
    public int value;

    // 入度
    public int in;

    // 出度
    public int out;

    // 邻居节点
    public ArrayList<Node> nexts;

    // 邻接边
    public ArrayList<Edge> edges;


    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
 
