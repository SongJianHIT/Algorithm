/**
 * @projectName Algorithm
 * @package data_structures.graph
 * @className data_structures.graph.TopologicalOrderDFS1
 */
package data_structures.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * TopologicalOrderDFS1
 * https://www.lintcode.com/problem/topological-sorting
 * @description
 * @author SongJian
 * @date 2022/12/13 15:22
 * @version
 */
public class TopologicalOrderDFS1 {

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
     * @description 记录当前节点和该节点所能到达的最大深度
     */
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            // 谁最大深度大，谁在前
            return o2.deep - o1.deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }


    /**
     * @title f
     * @author SongJian
     * @param: cur
     * @param: order
     * @updateTime 2022/12/13 15:27
     * @return: data_structures.graph.TopologicalOrderDFS1.Record
     * @throws
     * @description DFS查询当前节点的最大深度
     */
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            // 所有邻居中的最大深度
            follow = Math.max(follow, f(next, order).deep);
        }
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }
}
 
