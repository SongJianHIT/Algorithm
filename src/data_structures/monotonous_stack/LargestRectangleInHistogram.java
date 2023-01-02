/**
 * @projectName Algorithm
 * @package data_structures.monotonous_stack
 * @className data_structures.monotonous_stack.LargestRectangleInHistogram
 */
package data_structures.monotonous_stack;

import java.util.Stack;

/**
 * LargestRectangleInHistogram
 * @description 直方图最大面积
 * @author SongJian
 * @date 2023/1/2 11:32
 * @version
 */
public class LargestRectangleInHistogram {
    /**
     * @title largestRectangleArea
     * @author SongJian
     * @param: heights
     * @updateTime 2023/1/2 11:37
     * @return: int
     * @throws
     * @description
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - leftLessIndex - 1) * heights[popIndex];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (heights.length - leftLessIndex - 1) * heights[popIndex];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }
}

