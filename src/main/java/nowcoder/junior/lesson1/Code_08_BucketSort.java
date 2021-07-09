package nowcoder.junior.lesson1;

public class Code_08_BucketSort {
    public static void bucketSort(int[] arr){
        // 假设有1亿个数要排序, 并且每个数的值在0~200之间
        if(arr == null || arr.length < 2){
            return;
        }
        // 遍历数组找到最大值, 确定桶的个数
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        // 创建max+1个桶, 用于记录每个数出现的次数
        int[] bucket = new int[max + 1];
        for(int i = 0; i < arr.length; i++){
            // 数值i放第i个桶, 桶内计数+1
            bucket[arr[i]]++;
        }
        // 拷贝回原数组
        int i = 0;
        for(int j = 0; j < bucket.length; j++){
            // 第0个桶存放数0, 第1个桶存放数1...以此类推
            while(bucket[j]-- > 0){
                arr[i++] = j;
            }
        }
    }
}
