/**
 * @projectName Algorithm
 * @package data_structures.Unionfind_Sets
 * @className data_structures.Unionfind_Sets.UnionFind
 */
package data_structures.Unionfind_Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * UnionFind
 * @description 并查集
 * @author SongJian
 * @date 2022/12/8 15:00
 * @version
 */
public class UnionFind {

    /**
     * 包装类，包装一下对应的值
     * @param <V>
     */
    static class Node<V> {
        V value;
        public Node(V v) {
            value = v;
        }
    }

    /**
     * 并查集类
     * @param <V>
     */
    public static class UnionSet<V> {

        // 样本 --- 对应的节点
        public HashMap<V, Node<V>> nodes;

        // 不用通过指针的形式表示父亲节点，使用表来表示
        // 谁的直系父亲是谁
        public HashMap<Node<V>, Node<V>> parents;

        // 记录代表节点（帮主）与集合的大小（帮派人数）
        public HashMap<Node<V>, Integer> sizeMap;

        // 初始化，构造函数
        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * @title findFather
         * @author SongJian
         * @param: cur
         * @updateTime 2022/12/8 15:13
         * @return: data_structures.Unionfind_Sets.UnionFind.Node<V>
         * @throws
         * @description 给定一个节点 cur，一直向上找，直到找到代表节点，返回代表节点
         *              本方法有一个重要优化：让整棵树变扁平话，方便查找
         */
        public  Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                // 说明cur没到顶
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur == parents.get(cur)
            // 栈中元素改父亲，直接指向代表节点，后边后续查找
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        /**
         * @title isSameSet
         * @author SongJian
         * @param: a
         * @param: b
         * @updateTime 2022/12/8 15:14
         * @return: boolean
         * @throws
         * @description 查询 a 样本 与 b 样本 是否在同一个集合
         */
        public boolean isSameSet(V a, V b) {
            // 代表节点相同，则为同一个集合
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }
        
        /**
         * @title union
         * @author SongJian 
         * @param: a
         * @param: b
         * @updateTime 2022/12/8 15:16 
         * @throws
         * @description a 集合与 b 集合进行合并
         */
        public void union(V a, V b) {
            // 找到两个集合的代表节点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            // 如果 aHead == bHead，则说明已经是一个集合了，无需合并
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                // 大小集合头部
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                // 小集合的头直接指向大集合
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
    }
}
 
