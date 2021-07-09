package nowcoder.junior.lesson1;
import util.Swap;
/**
 * @description: 堆排序,
 * @author: zhoujie
 * @time: 2020/2/29 17:20
 */
public class Code_06_HeapSort {
    // 首先理解完全二叉树
    // 数组和二叉树的转换, 一个位置i的左孩子是2i+1,右孩子是2i+2, 父节点是(i-1)/2
    // 大/小根堆, 每一颗子树的最大/小值都是这颗子树的头部, 前提是完全二叉树, 建立大根堆的时间复杂度为O(N)
    // 大根堆的调整的时间复杂度为O(NlogN), 额外空间啊复杂度为O(1), 工程上少用堆排序的原因是不稳定, 且常数项较大
    public static void heapSort(int[] arr){
        if(arr == null && arr.length < 2){
            return;
        }
        // 从上往下建立大根堆
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }
        // 将大根堆根位置与最后一个数交换, 即将最大值交换到最后, 同时堆的大小减1,
        // 进行自上而下的调整, 将最大值转移到堆顶
        int size = arr.length;
        Swap.charSwap(arr, 0, --size);
        while(size > 0){
            // heapfy的过程中排好序
            heapify(arr, 0, size);
            Swap.charSwap(arr, 0, --size);
        }
    }

    public static void heapInsert(int[] arr, int index){
        // 构建大根堆, 每次进入的数都跟父节点比, 如果比父节点大, 则交换位置
        while(arr[index] > arr[(index - 1)/2]){
            Swap.charSwap(arr, index, (index - 1)/2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int size){
        int left = index * 2 + 1;
        while(left < size){
            // 找出左右孩子中值较大的孩子的下标
            int largest = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
            // 找出父节点与左右孩子中最大值的下标, 标记为largest
            largest = arr[index] < arr[largest] ? index : largest;
            // 当左右孩子和父节点的最大值的下标为父节点下标时, 表示此时的父节点就是大根堆的根节点, 无需移动
            while(index == largest){
                break;
            }
            // 否则交换位置, 同时将大根堆的index下移
            Swap.charSwap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }
}
