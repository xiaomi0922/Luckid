package nowcoder.junior.lesson1;

public class Code_08_BucketSort_MaxGap {
    public static int maxGap(int[] nums){
        if(nums == null || nums.length < 2){
            return 0;
        }
        // 找出列表的最大值和最小值
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        // 如果最大值和最小值相等, 则差值为0
        if(min == max){
            return 0;
        }
        // 构建桶, 桶的个数为数组长度+1, 只包含3个元素, 分别是桶中是否含有元素, 桶里面的最大值和桶里面的最小值
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;  // 第几号桶
        for(int i = 0; i < len; i++){
            bid = bucket(nums[i], len, min, max);
            // 第bid号桶中最大值和最小值存放
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        // 最大间隔不会出现在桶内, 因此计算桶间最大的间隔, 用相邻的非、空、桶的前一个桶最小值减去后一个桶最大值
        int res = 0;
        int lastMax = maxs[0];  // 前一个桶的最大值, mins[i] - lastMax为上一个桶的最小值减去前一个桶最大值
        for(int i = 1; i <= len; i++){
            if (hasNum[i]){
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num, int len, int min, int max){
        return (int) (num - min) * len / (max - min);
    }
}
