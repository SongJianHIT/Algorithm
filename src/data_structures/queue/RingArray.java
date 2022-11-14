/**
 * @projectName Algorithm
 * @package data_structures.queue
 * @className data_structures.queue.RingArray
 */
package data_structures.queue;

/**
 * RingArray
 * @description 使用循环数组实现队列
 * @author SongJian
 * @date 2022/11/14 17:21
 * @version
 */
public class RingArray {
    public static class MyQueue {
        private int[] arr;
        // 进来的数放哪
        private int push_index;
        // 从哪个位置弹出数据
        private int poll_index;
        // 队列容量
        private int size;
        // 数组总容量
        private final int limit;

        public MyQueue(int limit) {
            this.limit = limit;
            arr = new int[limit];
            push_index = 0;
            poll_index = 0;
            size = 0;
        }

        /**
         * @title push
         * @author SongJian
         * @param: value
         * @updateTime 2022/11/14 17:25
         * @throws
         * @description 向队列中添加元素
         */
        public void push (int value){
            if (size == limit)  {
                // 队列满
                throw new RuntimeException("队列满了，无法添加！！");
            }
            size++;
            arr[push_index] = value;
            push_index = nextIndex(push_index);
        }

        /**
         * @title pop
         * @author SongJian
         * @updateTime 2022/11/14 17:29
         * @return: int
         * @throws
         * @description 从队列中取元素
         */
        public int pop() {
            if (size == 0) {
                // 队列空
                throw new RuntimeException("队列位空，无法取值！！");
            }
            size--;
            int ans = arr[poll_index];
            poll_index = nextIndex(poll_index);
            return ans;
        }

        /**
         * @title isEmpty
         * @author SongJian
         * @updateTime 2022/11/14 17:31
         * @return: boolean
         * @throws
         * @description 判断队列是否为空
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * @title nextIndex
         * @author SongJian
         * @param: index
         * @updateTime 2022/11/14 17:30
         * @return: int
         * @throws
         * @description 如果现在下标为index，返回下一个下标的位置
         */
        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }
    }
}