/**
 * @projectName Algorithm
 * @package search.LeetCode154
 * @className search.LeetCode154.MinArray
 */
package search.LeetCode154;

/**
 * MinArray
 * @description
 * @author SongJian
 * @date 2022/12/1 09:17
 * @version
 */
public class MinArray {
    public int minArray(int[] numbers) {
        int l = 0;
        int r = numbers.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (numbers[mid] < numbers[r]) {
                r = mid;
            } else if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return numbers[l];
    }
}
 
