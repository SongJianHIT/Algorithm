/**
 * @projectName Algorithm
 * @package exclusive_or
 * @className exclusive_or.exclusiveOR
 */
package exclusive_or;

/**
 * exclusiveOR
 * @description 异或运算
 * @author SongJian
 * @date 2022/11/13 18:00
 * @version
 */
public class ExclusiveOR {

    /**
     * @title printOddTimesNum1
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 18:27
     * @throws
     * @description arr 数组中，只有一个数出现了奇数次，找出并打印
     */
    public static void printOddTimesNum1(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * @title printOddTimesNum2
     * @author SongJian
     * @param: arr
     * @updateTime 2022/11/13 18:55
     * @throws
     * @description arr 数组中，有两个不同的数出现了奇数次，找出并打印
     */
    public static void printOddTimesNum2(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // eor 一定不为0，因为两个不同的数
        // 取 eor 最右边的 1
        int rightOne = eor & (~eor + 1);
        // 其中一个结果
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0){
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    /**
     * @title onlyKTimes
     * @author SongJian
     * @param: a
     * @param: k
     * @param: m
     * @updateTime 2022/11/13 19:59
     * @return: int
     * @throws
     * @description 只有一种数出现了 K 次，剩下的数都出现了 M 次
     *              虽然有两个 for 循环，但内循环每次都干 32 次，并不是 N，所以时间复杂度还是 O(N)
     */
    public static int onlyKTimes(int[] arr, int k, int m){
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                // 遍历每个数的每一位，累加到 t 数组中
                t[i] += (num >> 1) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 若 t[i] 对 m 求余，余数不为 0，则说明 ans 在该位上是有 1 的
            // 反之亦然
            if ((t[i] % m) != 0) {
                // 说明 ans 在 i 位上是有 1 的
                // 这里用或，相当于相加
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
 
