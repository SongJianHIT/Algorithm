/**
 * @projectName Algorithm
 * @package algorithms.recursive
 * @className algorithms.recursive.PrintAllSubsquences
 */
package algorithms.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PrintAllSubsquences
 * @description 打印字符串
 * @author SongJian
 * @date 2022/12/14 11:32
 * @version
 */
public class PrintAllSubsquences {

    /**
     *  ========================================================================================
     *      打印字符串中所有的子序列
     */

    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process(str, 0, ans, path);
        return ans;
    }
    /**
     * @title process
     * @author SongJian 
     * @param: str 固定参数，字符串
     * @param: index 来到第几个位置
     * @param: ans 存储所有生成的子序列
     * @param: path 来到这个位置时，之前的决定
     * @updateTime 2022/12/14 11:34 
     * @throws 
     * @description
     */
    
    public static void process(char[] str, int index, List<String> ans, String path) {
        if(index == str.length) {
            // 决策过程终止
            ans.add(path);
            return;
        }
        // 第一个决策分支：不要当前元素
        String no = path;
        process(str, index + 1, ans, no);

        // 第二个决策分支：要当前的元素
        String yes = path + String.valueOf(str[index]);
        process(str, index + 1, ans, yes);
    }

    /**
     *  ========================================================================================
     *      打印字符串中所有的子序列，要求不能出现重复字面值的子序列
     */

    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if(index == str.length) {
            // 决策过程终止
            set.add(path);
            return;
        }
        // 第一个决策分支：不要当前元素
        String no = path;
        process2(str, index + 1, set, no);

        // 第二个决策分支：要当前的元素
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");
    }
}
 
