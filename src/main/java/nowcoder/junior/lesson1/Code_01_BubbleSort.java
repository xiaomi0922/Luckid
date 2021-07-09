package nowcoder.junior.lesson1;

import util.Swap;

/**
 * @description: 冒泡排序
 * @author: zhoujie
 * @time: 2020/2/24 15:15
 */
public class Code_01_BubbleSort {
    // 思路：从最后一个开始，每次从前向后比较每个数与后边的数的大小，将较大的后移，直到最后一位
    // 这样一轮下来，最大的数就排到最后一位，接着最后一位向前进一位，对应代码end--，依次循环。
    public static void bubbleSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        // 每次划定一个比较的终止位置，第一次是到最后一位终止，第二次是到倒数第二个位置终止
        for(int end=arr.length-1; end>0; end--){
            // 每次比较出一个最大的值，放到终止位置
            for(int i=1; i<end; i++){
                if(arr[i]>arr[end]){
                    Swap.charSwap(arr, i, i+1);
                }
            }
        }
    }

}
