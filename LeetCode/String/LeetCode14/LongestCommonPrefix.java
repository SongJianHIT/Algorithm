/**
 * @projectName Algorithm
 * @package String.LeetCode14
 * @className String.LeetCode14.LongestCommonPrefix
 */
package String.LeetCode14;

import java.util.Arrays;

/**
 * LongestCommonPrefix
 * @description 最长公共前缀
 * @author SongJian
 * @date 2022/11/17 07:35
 * @version
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        int length = strs[0].length();
        int count = strs.length;
        for(int i = 0; i < length; i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != ch){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}