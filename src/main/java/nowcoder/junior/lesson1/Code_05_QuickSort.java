package nowcoder.junior.lesson1;
import util.Swap;

import java.sql.SQLClientInfoException;

/**
 * @description: 快速排序, 不稳定, 一般来说时间复杂度为O(N*logN)
 * @author: zhoujie
 * @time: 2020/2/29 16:19
 */
public class Code_05_QuickSort {
    // 先用荷兰国旗问题划分出大于、小于、等于某一值的三块区域, 得到等于值区域的下标
    // 然后递归调用快速排序解决
    public static void quickSort(int[] arr, int left, int right){
        if(left < right){
            // 随机取一个数与最后一个数交换
            Swap.charSwap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            // 根据荷兰国旗问题选取出大于等于小于的区域, 返回p为等于区域的下标值, p[0]和p[1]分别是等于区域的左下边和右下标
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }

    public static int[] partition(int[] arr, int left, int right){
        // 定义区域的边界, 小于区域为left-1不包含第一个值, 大于区域为right包含最后一个值
        int less = left - 1;
        int more = right;
        while(left < more){
            // 如果左侧的数小于指定的数, 则小于边界右扩一位, swap并没交换, left右移一位
            if(arr[left] < arr[right]){
                Swap.charSwap(arr, ++less, left++);
            }
            // 如果左侧的数大于指定的数, 则大于边界先左扩一位, 与左侧数互换, 同时左侧数据位置保持不变, 继续下一轮比较
            else if(arr[left] > arr[right]){
                Swap.charSwap(arr, --more, left);
            }
            // 如果相等, 则左边界不变, left值右移一位
            else {
                left++;
            }
        }
        // 将大于边界第一个数与最后一个指定的数交换
        Swap.charSwap(arr, more, right);
        // 返回等于区域的左右边界, 为小于边界+1和大于边界
        return new int[] {less + 1, more};
    }
}
