/**
 * @projectName Algorithm
 * @package algorithms.recursive
 * @className algorithms.recursive.PrintAllPermutations
 */
package algorithms.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * PrintAllPermutations
 * @description 打印字符串的全排列
 * @author SongJian
 * @date 2022/12/14 11:52
 * @version
 */
public class PrintAllPermutations {
    /**
     *  ========================================================================================
     *      打印字符串的全部排列，版本一
     */

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    public static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                f(rest, path + cur, ans);
                // 恢复现场
                rest.add(i, cur);
            }
        }
    }

    /**
     *  ========================================================================================
     *      打印字符串的全部排列，版本二
     */

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        String path = "";
        g(str, 0, ans);
        return ans;
    }

    /**
     * @title g
     * @author SongJian
     * @param: str
     * @param: index
     * @param: ans
     * @updateTime 2022/12/14 12:05
     * @throws
     * @description 之前做过的决定就已经保留在 String 字符数组 str 中
     */

    public static void g(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            // 终止
            ans.add(String.valueOf(str));
        } else {
            // index ->
            for (int i = index; i < str.length; ++i) {
                swap(str, index, i);
                g(str, index + 1, ans);
                // 恢复现场
                swap(str, index, i);
            }
        }
    }
    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }


    /**
     *  ========================================================================================
     *      打印字符串的全部排列，版本二
     */
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        String path = "";
        g2(str, 0, ans);
        return ans;
    }

    /**
     * @title g
     * @author SongJian
     * @param: str
     * @param: index
     * @param: ans
     * @updateTime 2022/12/14 12:05
     * @throws
     * @description 之前做过的决定就已经保留在 String 字符数组 str 中
     */

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            // 终止
            ans.add(String.valueOf(str));
        } else {
            // index位置跟i位置交换（只有i位置不曾试过的才交换）
            boolean[] visited = new boolean[255];
            for (int i = index; i < str.length; ++i) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    // 恢复现场
                    swap(str, index, i);
                }
            }
        }
    }


    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }
}
 
