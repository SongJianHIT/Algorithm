/**
 * @projectName Algorithm
 * @package Sort.QuickSort
 * @className Sort.QuickSort.LeetCode283
 */
package Sort.QuickSort;

/**
 * LeetCode283: https://leetcode.cn/problems/move-zeroes/description/
 * @description
 * @author SongJian
 * @date 2022/11/18 12:24
 * @version
 */
public class MoveZeroes1 {
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        // p2 维护了非0边界
        int p2 = -1;
        for(int p1 = 0; p1 < nums.length; p1++) {
            //当前元素!=0，就与边界尾部的下一个元素进行交换，并且扩大边界
            if(nums[p1]!=0) {
                int tmp = nums[p1];
                nums[p1] = nums[p2+1];
                nums[p2+1] = tmp;
                p2++;
            }
        }
    }
}
 
