/**
 * @projectName Algorithm
 * @package backtracking.LeetCode17
 * @className backtracking.LeetCode17.LetterCombinations
 */
package backtracking.LeetCode17;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterCombinations
 * @description 17. 电话号码的字母组合
 * @author SongJian
 * @date 2022/11/17 19:27
 * @version
 */
public class LetterCombinations {
    // 存储结果
    private List<String> res = new ArrayList<>();
    // 数字、字符表
    private String[] letterMap = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };
    public List<String> letterCombinations (String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // 进入回溯
        backtracking(digits, 0);
        return res;
    }

    private StringBuilder tmp = new StringBuilder();

    // 回溯方法
    public void backtracking (String digits, int index) {
        // 回溯终止条件
        if (index == digits.length()) {
            // 如果index=字符串长度，则说明已经递归到字符串尾部
            // 保存子结果
            res.add(tmp.toString());
            return;
        }
        // 回溯未终止，则遍历当前数字对应的字符集，逐一回溯
        String str = letterMap[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            // 拼接
            tmp.append(str.charAt(i));
            // 执行回溯
            backtracking(digits, index + 1);
            // 为了不影响下次回溯，需要删除尾部元素
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
}
 
