package nowcoder.junior.lesson1;

/**
 * @description: 归并排序, 稳定的, 时间复杂度为O(logN), 空间复杂度为O(N), 因需要额外准备数组
 * @author: zhoujie
 * @time: 2020/2/29 17:10
 */
public class Code_04_MergeSort {
    public static void mergeSort(int [] arr){
        if(arr == null || arr.length<2){
            return;
        }
        mergeSort(arr, 0, arr.length-1);
    }

    public static void mergeSort(int [] arr, int left, int right){
        int mid = left + (right-left)/2;
        
    }
}
