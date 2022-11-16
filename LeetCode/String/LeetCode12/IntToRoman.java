/**
 * @projectName Algorithm
 * @package String.LeetCode12
 * @className String.LeetCode12.IntToRoman
 */
package String.LeetCode12;

/**
 * IntToRoman
 * @description LeeetCode12：整数转罗马数字
 * @author SongJian
 * @date 2022/11/16 10:16
 * @version
 */
public class IntToRoman {
    private int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private String[] str = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD",
            "D", "CM", "M"};

    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        // 遍历 values 数组
        for (int i =  values.length - 1; i >= 0; i--) {
            while (num >= values[i]) {
                sb.append(str[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }
}
 
