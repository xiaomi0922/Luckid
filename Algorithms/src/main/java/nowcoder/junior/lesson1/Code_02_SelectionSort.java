package nowcoder.junior.lesson1;

/**
 * @description: 选择排序
 * @author: zhoujie
 * @time: 2020/2/26 15:43
 */

import util.Swap;

public class Code_02_SelectionSort {
    // 思路：每次找出最小的一个数的位置，放在第一个位置，再从之后的找到最小的数，放第二个位置
    // 依次循环，不稳定，时间复杂度O(N)，空间复杂度O(1)
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        for(int i=0; i<arr.length; i++){
            // 每次将最小的位置设为i
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[i]){
                    minIndex = arr[j] < arr[minIndex] ? j : minIndex;
                }
            }
            Swap.charSwap(arr, i, minIndex);;
        }
    }
}
