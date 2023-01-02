/**
 * @projectName Algorithm
 * @package data_structures.monotonous_stack
 * @className data_structures.monotonous_stack.MaximalRectangle
 */
package data_structures.monotonous_stack;

import java.util.Stack;

/**
 * MaximalRectangle
 * https://leetcode.com/problems/maximal-rectangle/
 * @description
 * @author SongJian
 * @date 2023/1/2 11:51
 * @version
 */
public class MaximalRectangle {
    /**
     * @title maximalRectangle
     * @author SongJian
     * @param: map
     * @updateTime 2023/1/2 12:06
     * @return: int
     * @throws
     * @description
     */
    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    /**
     * @title maxRecFromBottom
     * @author SongJian
     * @param: height 直方图数组
     * @updateTime 2023/1/2 12:09
     * @return: int
     * @throws
     * @description
     */
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; ++i) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int popIndex = stack.pop();
                int leftLessIdx = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - leftLessIdx - 1) * height[popIndex];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIdx = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - leftLessIdx - 1) * height[popIndex];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}

