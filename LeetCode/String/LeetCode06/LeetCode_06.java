/**
 * @projectName Algorithm
 * @package String
 * @className String.LeetCode06.LeetCode_06
 */
package String.LeetCode06;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode_06
 * @description 6. Z 字形变换，https://leetcode.cn/problems/zigzag-conversion/description/
 *              优秀题解：https://leetcode.cn/problems/zigzag-conversion/solutions/9490/6-z-zi-xing-bian-huan-c-c-by-bian-bian-xiong/
 * @author SongJian
 * @date 2022/11/14 08:51
 * @version
 */
public class LeetCode_06 {
    /**
     * @title convert
     * @author SongJian
     * @param: s
     * @param: numRows
     * @updateTime 2022/11/14 08:53
     * @return: java.lang.String
     * @throws
     * @description 解法一：基于顺序读取，特殊存储方式。时间复杂度为 O(N)
     */
    public static String convert (String s, int numRows) {
        if (numRows < 2) return s;
        // 1、使用 arraylist 和 stringbuilder 记录每一行
        List<StringBuilder> rows = new ArrayList<>();
        // 2、初始化 arraylist，创建里面的 sb 对象
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // 3、初始化 flag 和 index
        // index：用于判断当前字符添加到哪一行中的 sb
        // flag：用于判断 index 如何变化，当遍历当边界时，需要进行返向
        int flag = -1, index = 0;
        // 4、开始遍历
        for (char ch : s.toCharArray()) {
            // 添加到对应 row 的 sb 中
            rows.get(index).append(ch);
            // 判断是否需要反向
            if (index == 0 || index == numRows - 1) flag = -flag;
            index += flag;
        }
        // 5、处理结果并返回
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rows) {
            res.append(sb);
        }
        return res.toString();
    }

    /**
     * @title convertRules
     * @author SongJian
     * @param: s
     * @param: numRows
     * @updateTime 2022/11/14 09:13
     * @return: java.lang.String
     * @throws
     * @description 解法二：基于规律
     */
    public static String convertRules(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuilder res = new StringBuilder();
        // 找规律得出
        int step = 2 * numRows - 2;
        int len = s.length();
        // add:记录真实间距
        int add = 0;
        // index:记录 s 的下标
        int index = 0;
        for (int i = 0; i < numRows; i++){
            index = i;
            add = 2 * i;
            while (index < len) {
                res.append(s.charAt(index));
                add = step - add;
                // 0行和最后一行使用 step 间距，其余使用 add 间距
                index += (i == 0 || i == numRows-1) ? step : add;
            }
        }
        return res.toString();
    }
}
 
