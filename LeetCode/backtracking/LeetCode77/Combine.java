/**
 * @projectName Algorithm
 * @package backtracking.LeetCode77
 * @className backtracking.LeetCode77.Combine
 */
package backtracking.LeetCode77;

import java.util.ArrayList;
import java.util.List;

/**
 * Combine
 * @description 77.组合
 * @author SongJian
 * @date 2022/11/17 21:11
 * @version
 */
public class Combine {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n < 1 || k > n) return res;
        backtracking(n, k, 1);

        return res;
    }

    // 回溯方法
    public void backtracking(int n, int k, int startIndex) {
        if (k == tmp.size()) {
            // 这里需要 new 对象，并且拷贝内容
            // 否则 res 里存储的是 tmp 的地址，最后全为空
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i < k ;i++){
                temp.add(tmp.get(i));
            }
            res.add(temp);
            return;
        }
        // 控制树的横向遍历
        for (int i = startIndex; i <= n; i++) {
            // 处理节点
            tmp.add(i);
            // 递归：控制树的纵向遍历，注意下一层搜索要从 i+1 开始
            backtracking(n, k, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
 
