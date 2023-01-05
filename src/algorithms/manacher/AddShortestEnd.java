/**
 * @projectName Algorithm
 * @package algorithms.manacher
 * @className algorithms.manacher.AddShortestEnd
 */
package algorithms.manacher;

/**
 * AddShortestEnd
 * @description
 * @author SongJian
 * @date 2023/1/5 14:41
 * @version
 */
public class AddShortestEnd {

    /**
     * @title shortestEnd
     * @author SongJian
     * @param: s
     * @updateTime 2023/1/5 14:42
     * @return: java.lang.String
     * @throws
     * @description 向字符串 s 中添加最少字符，使其变为回文串
     */
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < str.length; ++i) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                C = i;
                R = i + pArr[i];
            }
            // 第一次包括最后一个元素
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; ++i) {
            // 逆序填
            res[res.length - 1 - i] = str[2 * i + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }

}

