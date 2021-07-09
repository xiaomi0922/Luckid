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
        int mid = left + (right-left) >> 1;  // 右移一位相当于除以2
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr, left, mid, left);
    }

    public static void merge(int[] arr, int left, int mid, int right){
        int[] help = new int[right-left+1];
        int i = 0;
        int p1 = left;
        int p2 = mid+1;
        while(p1 < mid && p2 < right){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 实际只有一个while会越界, 如果p1没越界则将p1后边的数都拷贝到help数组中,
        // 如果p2没越界, 则将p2后边的数都拷贝到help数组中
        while(p1<=mid){
            help[i++] = arr[p1++];
        }
        while(p2<=right){
            help[i++] = arr[p2++];
        }
        // 从help数组拷贝回原数组
        for(i = 0; i < help.length; i++){
            arr[left+1] = help[i];
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 8, 6, 9, 4};
    }
}
