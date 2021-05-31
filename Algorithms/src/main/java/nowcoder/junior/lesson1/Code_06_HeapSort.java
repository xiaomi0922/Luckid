package nowcoder.junior.lesson1;

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

}
