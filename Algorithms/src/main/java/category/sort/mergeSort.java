package category.sort;

/**
 * @description: 归并排序
 * @author: zhoujie
 * @time: 2020/1/20 17:24
 */
public class mergeSort {
    public static void mergeSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        mergeSort(array, 0, array.length-1);
    }

    public static void mergeSort(int[] array, int left, int right) {
        int mid = left + (right - left)>>1;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, right, mid);
    }

    public static void merge(int[] array, int left, int right, int mid){
        int[] tmp = new int[right-left+1];
        int i = 0;
        int p1 = left;
        int p2 = mid+1;
        while(p1<=mid && p2<=right){
            tmp[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

    }
}

