package nowcoder.junior.lesson1;

import util.Swap;
import java.util.Arrays;

/**
 * @description: 插入排序
 * @author: zhoujie
 * @time: 2020/2/26 15:54
 */
public class Code_03_InsertionSort {
    // 思路：假设前面的数字都是排好序的，每次来一个新的数据，都跟前面的比较，然后插入
    public static void insertionSort(int [] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 从第一个位置开始看，比较其与前一个位置(0位置)的大小，若小，则交换
        for(int i=1; i<arr.length; i++){
            // 每比较完一次，所有的数据都是排好序的，下次来了数据根据大小插入适当的位置
            for(int j=i-1; j>=0 && arr[j]>arr[j+1]; j--){
                Swap.charSwap(arr, j, j+1);
            }
        }
    }
}
