/**
 * @projectName Algorithm
 * @package algorithms.greedy
 * @className algorithms.greedy.IPO
 */
package algorithms.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * IPO
 * @description 项目利润问题
 * @author SongJian
 * @date 2022/12/8 10:54
 * @version
 */
public class IPO {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> queueSmall = new PriorityQueue<>(new smallHeap());
        PriorityQueue<Program> queueBig = new PriorityQueue<>(new bigHeap());
        // 构建小根堆
        for (int i = 0; i < Profits.length; ++i) {
            queueSmall.add(new Program(Profits[i], Capital[i]));
        }
        // K 轮，拿 K 个项目
        for (int i = 0; i < K; ++i) {
            while (!queueSmall.isEmpty() && queueSmall.peek().c <= W) {
                // 小根堆不为空，并且现在有的钱能够买足cost
                queueBig.add(queueSmall.poll());
            }
            if (queueBig.isEmpty()) {
                // 现在的钱不够开始任何项目，即会空
                return W;
            }
            W += queueBig.poll().p;
        }
        return W;
    }

    // 小根堆排序规则，按照代价排
    static class smallHeap implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    // 小根堆排序规则，按照代价排
    static class bigHeap implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
 
