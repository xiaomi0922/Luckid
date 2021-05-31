package offer;

public class num_001 {
    /**
     * @Author Luckid
     * @Description 二维数组查找
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @Date 10:07 2021/5/31
     **/
    public static boolean Find(int target, int[][] array){
        int row = 0;
        int col = array.length - 1;
        while(row<array.length-1 && col>0){
            if(target > array[row][col]){
                row++;
            }
            else if(target < array[row][col]){
                col--;
            }
            else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int a[][] = {{1,2,8,9}, {2,4,9,12}, {4,7,10,13}, {6,8,11,15}};
        System.out.println(Find(7, a));
        System.out.println(Find(3, a));
    }
}
