/**
 * @projectName Algorithm
 * @package algorithms.kmp
 * @className algorithms.kmp.KMP
 */
package algorithms.kmp;

/**
 * KMP
 * @description
 * @author SongJian
 * @date 2023/1/4 12:18
 * @version
 */
public class KMP {

    /**
     * @title getIndexOf
     * @author SongJian
     * @param: s1 原始串
     * @param: s2 模式串
     * @updateTime 2023/1/4 12:19
     * @return: int
     * @throws
     * @description 在 s1 中匹配 s2，返回匹配成功的起始下标
     */
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 两个字符串的当前匹配位置
        // 返回结果可以通过 x - y 计算出
        int x = 0;
        int y = 0;
        // 计算模式串的 next 数组
        // O(M) m <= n
        int[] next = getNextArray(str2);
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                // 也就是 y == 0，y 无法向左推了
                // 也就是现在 str1 中的 x 位置与 str2 的 0 位置都不相等
                x++;
            } else {
                // y 还能够向左推
                y = next[y];
            }
        }
        // 只有 y 越界了，才说明 str2 匹配完成了！
        // 如果 y 没越界，x 越界了，说明遍历完 str1 还是无法成功匹配，匹配失败
        return y == str2.length ? x - y : -1;
    }

    /**
     * @title getNextArray
     * @author SongJian
     * @param: str2
     * @updateTime 2023/1/4 12:30
     * @return: int[]
     * @throws
     * @description 计算 str2 的 next 数组
     */
    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        // i 用来遍历 str2
        int i = 2;
        // 当前是哪个位置的值再和 i-1 位置的字符比较
        int cn = 0;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

