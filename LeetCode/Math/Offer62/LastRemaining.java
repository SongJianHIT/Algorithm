/**
 * @projectName Algorithm
 * @package Math.Offer62
 * @className Math.Offer62.LastRemaining
 */
package Math.Offer62;

/**
 * LastRemaining
 * @description 约瑟夫环问题
 *              https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * @author SongJian
 * @date 2022/12/20 09:26
 * @version
 */
public class LastRemaining {
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }
}

