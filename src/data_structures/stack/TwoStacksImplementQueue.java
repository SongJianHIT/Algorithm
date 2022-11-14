/**
 * @projectName Algorithm
 * @package data_structures.stack
 * @className data_structures.stack.TwoStacksImplementQueue
 */
package data_structures.stack;

import java.util.Stack;

/**
 * TwoStacksImplementQueue
 * @description 使用两个栈实现队列
 * @author SongJian
 * @date 2022/11/14 20:04
 * @version
 */
public class TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        /**
         * @title pushToPop
         * @author SongJian
         * @updateTime 2022/11/14 20:06
         * @throws
         * @description 从 push 栈向 pop 栈中倒入数据
         */
        private void pushToPop() {
            if (stackPop.empty()) {
                // 如果 pop 栈中有元素，则不进行倒入
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        /**
         * @title peek
         * @author SongJian
         * @updateTime 2022/11/14 20:05
         * @return: int
         * @throws
         * @description 获取队首元素
         */
        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
 
