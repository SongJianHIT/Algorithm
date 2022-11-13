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
}
 
