/**
 * @projectName Algorithm
 * @package algorithms.recursive
 * @className algorithms.recursive.ReverseStackUsingRecursive
 */
package algorithms.recursive;

import java.util.Stack;

/**
 * ReverseStackUsingRecursive
 * @description 逆序栈
 * @author SongJian
 * @date 2022/12/14 12:24
 * @version
 */
public class ReverseStackUsingRecursive {


    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 移除栈底元素并返回
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * @title f
     * @author SongJian
     * @param: stack
     * @updateTime 2022/12/14 12:24
     * @return: int
     * @throws
     * @description 栈底元素移除掉，上面的元素盖下来，返回移除掉的栈底元素
     */
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
 
