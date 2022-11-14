/**
 * @projectName Algorithm
 * @package Integer.LeetCode07
 * @className Integer.LeetCode07.Solution
 */
package Integer.LeetCode07;

/**
 * Solution
 * @description 7. 整数反转  https://leetcode.cn/problems/reverse-integer/description/
 * @author SongJian
 * @date 2022/11/14 10:18
 * @version
 */
public class Solution {
    public int reverse(int x) {
        if (x == 0) return 0;
        int res = 0;
        int cur = 0;
        while (x != 0) {
            cur = x % 10;
            // 重点：这里就需要判断是否出现越界！！
            if (res > 214748364 || (res == 214748364 && cur > 7)){
                // 大于 int 最大整数范围
                return 0;
            }
            if (res < -214748364 || (res == -214748364 && cur < -8)){
                return 0;
            }
            res = res * 10 + cur;
            x /= 10;
        }
        return res;
    }
}
 
