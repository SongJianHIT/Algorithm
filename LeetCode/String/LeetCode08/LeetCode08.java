/**
 * @projectName Algorithm
 * @package String.LeetCode08
 * @className String.LeetCode08.LeetCode08
 */
package String.LeetCode08;

import java.time.chrono.MinguoDate;

/**
 * LeetCode08
 * @description 08. 字符串转换整数 (atoi)
 *              https://leetcode.cn/problems/string-to-integer-atoi/description/
 * @author SongJian
 * @date 2022/11/15 08:34
 * @version
 */
public class LeetCode08 {
    /**
     * @title myAtoi
     * @author SongJian
     * @param: s
     * @updateTime 2022/11/15 08:35
     * @throws
     * @description 将字符串转化为整数，需要判断溢出，排除其他字符
     */
    public int myAtoi(String s) {
        if (s.length() < 0 || s == null) {
                return 0;
        }
        // 记录字符串长度和字符数组
        int len = s.length();
        char[] charArray = s.toCharArray();

        // 先排除数字前的空格
        int start = 0;
        while (start < len && charArray[start] == ' ') start++;

        // 排除空格完成
        // 但这里还需要判断一个极端情况，即整个字符串全是空格
        if (start == len) return 0;

        // 符号字符
        int flag = 1;
        char firstChar = charArray[start];
        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            flag = -1;
            start++;
        }

        // 开始遍历处理剩下有效的字符
        int res = 0;
        while (start < len) {
            char cur = charArray[start];
            if (cur > '9' || cur < '0') {
                // 遇到不合法字符，退出循环，返回res=0
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (cur - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (cur - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + flag * (cur - '0');
            start++;
        }
        return res;
    }
}
 
