/**
 * @projectName Algorithm
 * @package algorithms.search.TrieTree
 * @className algorithms.search.TrieTree.Tire
 */
package algorithms.search.TrieTree;

/**
 * Tire
 *
 * @author SongJian
 * @description 前缀树
 * @date 2022/11/29 09:26
 */
public class TireTree {

    /**
     * 前缀树的节点类
     */
    public static class Node1 {
        public int pass;
        public int end;
        // 数组存储到下一层有多少路
        public Node1[] nexts;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            // 只考虑小写英文字符，所以只准备了长度为26的数组
            // 0...a
            // 1...b
            // 2...c
            // nexts[i] == null  i方向的路不存在
            // nexts[i] != null  i方向的路存在
            this.nexts = new Node1[26];
        }
    }

    /**
     * 前缀树类
     */
    public static class Trie1 {
        // 头节点
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        /**
         * 向添加字符串
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            // 指向头节点
            Node1 node = root;
            node.pass++;
            int path = 0;
            // 从左往右遍历字符
            for (int i = 0; i < str.length; i++) {
                // 当前字符要找哪条路
                path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    // 说明之前的添加的字符串没有对应的路
                    // 新建节点
                    node.nexts[path] = new Node1();
                }
                // node往下走（不管是新建的，还是复用的，必存在）
                node = node.nexts[path];
                node.pass++;
            }
            // 遍历字符结束
            node.end++;
        }

        /**
         * 在前缀树中，查找word出现了几次
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            // 指向头节点
            Node1 node = root;
            int index = 0;
            // 从左往右遍历字符
            for (int i = 0; i < str.length; i++) {
                // 当前字符要找哪条路
                index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    // 说明当前树中没有该字符串
                    return 0;
                }
                // node往下走（不管是新建的，还是复用的，必存在）
                node = node.nexts[index];
            }
            // 遍历字符结束
            return node.end;
        }

        /**
         * 在前缀树中，有多少个是以 pre 为前缀的字符串
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] str = pre.toCharArray();
            // 指向头节点
            Node1 node = root;
            int index = 0;
            // 从左往右遍历字符
            for (int i = 0; i < str.length; i++) {
                // 当前字符要找哪条路
                index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    // 说明当前树中没有该字符串
                    return 0;
                }
                // node往下走（不管是新建的，还是复用的，必存在）
                node = node.nexts[index];
            }
            // 遍历字符结束
            return node.pass;
        }

        /**
         * 在前缀树中，删除字符串
         */
        public void delete(String word) {
            // 先查一下树中有没有
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    // 当前节点的下一个节点的pass减1
                    // 如果减1后为0，则删除
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }
    }
}
 
