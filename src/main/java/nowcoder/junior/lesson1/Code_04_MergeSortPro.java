package nowcoder.junior.lesson1;

/**
 * @description: 最小和问题，mergeSort的应用
 * @author: zhoujie
 * @time: 2020/2/29 16:10
 */    
public class Code_04_MergeSortPro {
    // 小和数smallSum问题, 求一个列表各个位上左侧小于自己的数的和
    // 思路：在归并排序的中, merger的过程产生小和, 划分的过程不产生小和
    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int left, int right){
        if(left == right){
            return 0;
        }
        int mid = left + (right - left) >> 1;
        return mergeSort(arr, left, mid) + mergeSort(arr,mid+1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right){
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;
        while(p1 < mid && p2 < right){
            res += arr[p1] < arr[p2] ? arr[p1] * (right-p2+1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2 <= right){
            help[i++] = arr[p2++];
        }
        for(i = 0; i < help.length; i++){
            arr[left + 1] = help[i];
        }
        return res;
    }
}
