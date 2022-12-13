/**
 * @projectName Algorithm
 * @package Heap.Offer41
 * @className Heap.Offer41.MedianFinder
 */
package Heap.Offer41;

import java.util.PriorityQueue;

/**
 * MedianFinder
 * @description
 * @author SongJian
 * @date 2022/12/13 10:16
 * @version
 */
public class MedianFinder {
    /** initialize your data structure here. */
    /**
     * 大根堆记录小于平均值的元素
     */
    PriorityQueue<Integer> queueMax;
    /**
     * 小根堆记录大于平均值的元素
     */
    PriorityQueue<Integer> queueMin;
    public MedianFinder() {
        queueMin = new PriorityQueue<>();
        queueMax = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        // 如果不等，肯定是小根堆（存较大元素的）多
        // 因此要插入大根堆。
        if (queueMin.size() != queueMax.size()) {
            queueMin.offer(num);
            queueMax.offer(queueMin.poll());
        } else {
            // 相等时，插入小根堆
            queueMax.offer(num);
            queueMin.offer(queueMax.poll());
        }
    }

    public double findMedian() {
        return queueMax.size() != queueMin.size() ? queueMin.peek() : (queueMax.peek() + queueMin.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

 
