/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.StickersToSpellWord
 */
package algorithms.dynamic_programming;

import java.util.HashMap;

/**
 * StickersToSpellWord
 * @description https://leetcode.cn/problems/stickers-to-spell-word/
 * @author SongJian
 * @date 2022/12/20 16:09
 * @version
 */
public class StickersToSpellWord {

    /**
     * ==============================================================================================================
     * 暴力递归
     * @title minStickers1
     * @author SongJian
     * @param: stickers
     * @param: target
     * @updateTime 2022/12/20 16:18
     * @return: int
     * @throws
     * @description
     */
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * @param stickers 所有种类的贴纸，每一种贴纸都有无穷张
     * @param target 要拼出的目标
     * @return 最小张数
     */
    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            // base case：拼好了
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            // 每一张贴纸都作为第一张的情况下
            // 剩下的字符串
            String rest = minus(target, first);
            // rest.length() = target.length() 当前贴纸没有减少任何字符
            if (rest.length() != target.length()) {
                // 剩下的字符串rest继续暴力递归
                min = Math.min(min, process1(stickers, rest));
            }
        }
        // min = MAX_VALUE 意味着无法解决
        return min + (min == Integer.MAX_VALUE ?  0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char ch : str1) {
            count[ch - 'a']++;
        }
        for (char ch : str2) {
            count[ch - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; ++j) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }

    /**
     * ==============================================================================================================
     * 优化：词频表 + 剪枝
     * @title minStickers2
     * @author SongJian
     * @param: stickers
     * @param: target
     * @updateTime 2022/12/20 16:44
     * @return: int
     * @throws
     * @description
     */
    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * @param stickers 当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
     * @param t 目标
     * @return 返回搞定target的最少张数
     */
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /**
     * ==============================================================================================================
     * 优化：使用 Map 傻缓存
     * @title minStickers3
     * @author SongJian
     * @param: stickers
     * @param: target
     * @updateTime 2022/12/20 16:47
     * @return: int
     * @throws
     * @description
     */
    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            // 表里有就直接拿
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        // 存表
        dp.put(t, ans);
        return ans;
    }
}

