/**
 * @projectName Algorithm
 * @package backtracking.LeetCode784
 * @className backtracking.LeetCode784.LetterCasePermutation
 */
package backtracking.LeetCode784;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterCasePermutation
 * @description
 * @author SongJian
 * @date 2022/11/26 10:46
 * @version
 */
public class LetterCasePermutation {
    char[] cs;
    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        cs = s.toCharArray();
        dfs(0, s.length(), new char[s.length()]);
        return ans;
    }

    /**
     idx : 当前遍历深度
     n : 字符串总长度
     cur: 当前临时数组
     */
    void dfs(int idx, int n, char[] cur) {
        if (idx == n) {
            ans.add(String.valueOf(cur));
            return ;
        }
        // 第一种，当前元素不变，直接backtracking
        cur[idx] = cs[idx];
        dfs(idx + 1, n, cur);

        // 第二种，对字母大小写进行变化，然后再backtracking
        if (Character.isLetter(cs[idx])) {

            // 可以通过跟 32 进行异或来进行大小写转换
            cur[idx] = (char) (cs[idx] ^ 32);
            dfs(idx + 1, n, cur);
        }
    }
}
 
