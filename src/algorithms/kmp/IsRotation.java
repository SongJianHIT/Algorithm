/**
 * @projectName Algorithm
 * @package algorithms.kmp
 * @className algorithms.kmp.IsRotation
 */
package algorithms.kmp;

/**
 * IsRotation
 * @description 判断旋转字符串
 * @author SongJian
 * @date 2023/1/5 21:28
 * @version
 */
public class IsRotation {

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }

    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] mm = m.toCharArray();
        int[] next = getNextArr(mm);
        int si = 0;
        int mi = 0;
        while (si < ss.length && mi < mm.length) {
            if (ss[si] == mm[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == mm.length ? si - mi : -1;
    }

    public static int[] getNextArr(char[] chars) {
        if (chars.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int pos = 2;
        while (pos < chars.length) {
            if (chars[pos - 1] == chars[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "deabc";
        System.out.println(isRotation(str1, str2));

    }
}

