/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.TopologicalOrderDFS2
 */
package data_structures.graph;

import algorithms.greedy.LowestLexicography;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * TopologicalOrderDFS2
 * OJ链接：https://www.lintcode.com/problem/topological-sorting
 * @description
 * @author SongJian
 * @date 2022/12/13 14:51
 * @version
 */
public class TopologicalOrderDFS2 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }


    // 提交下面的
    /**
     * @title
     * @author SongJian
     * @updateTime 2022/12/13 15:23
     * @throws
     * @description 记录当前节点和当前节点的后续点次
     */

    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }


    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }

    /**
     * @title topSort
     * @author SongJian
     * @param: graph
     * @updateTime 2022/12/13 14:59
     * @return: java.util.ArrayList<data_structures.graph.TopologicalOrderDFS2.DirectedGraphNode>
     * @throws
     * @description 给定有向无环图 graph，返回图的拓扑序
     */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 构建缓存表
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 计算图中所有节点的点次
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        // 取出所有的点次，并排序
        ArrayList<Record> records = new ArrayList<>();
        for (Record r : order.values()) {
            records.add(r);
        }
        records.sort(new MyComparator());
        // 根据排序结果
        // 点次高的节点，拓扑序在前
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : records) {
            ans.add(r.node);
        }
        return ans;
    }

    /**
     * @title f
     * @author SongJian
     * @param: cur 当前点
     * @param: order 缓存
     * @updateTime 2022/12/13 14:53
     * @return: data_structures.graph.TopologicalOrderDFS2.Record
     * @throws
     * @description 当前来到 cur 节点，返回 cur 点所到之处，所有的点次
     */
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            // 先查缓存，如果算过了，就直接拿
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            // 计算所有邻居的点次之和
            // dfs
            nodes += f(next, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        // 放缓存
        order.put(cur, ans);
        return ans;
    }
}
 
