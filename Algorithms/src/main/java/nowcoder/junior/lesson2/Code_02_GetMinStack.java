package nowcoder.junior.lesson2;

import javax.naming.InsufficientResourcesException;
import java.util.Stack;

public class Code_02_GetMinStack {
    /**
     * @Author zhoujie
     * @Description 实现一个特殊的栈, 在实现栈的基本操作的基础上, 再实现返回栈中最小元素的操作, 要求时间复杂度为O(1)
     * @Date 15:25 2021/7/8
     */
    public static class MyStack1{
        // 思路：设计两个栈, 一个栈存放数据, 一个栈存放最小元素, 当有数据要push进来时, 如果比stackMin的栈顶元素小
        // 则压入stackMin, 否则不压入, 取数据时, 先判断取的数据是否与stackMin的栈顶元素相等, 相等则同步弹出,
        // 否则stackData栈弹出
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int num){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(num);
            }
            else if(num <= this.getmin()){
                this.stackMin.push(num);
            }
            this.stackData.push(num);
        }

        public int pop(){
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if(value == this.getmin()){
                this.stackMin.pop();
            }
            return value;
        }

        // 实现O(1)时间复杂度的得到栈的最小元素操作
        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static class MyStack2 {
        // 思路2：准备两个栈, 同步压入数据, 当压入的数据比stackMin小时, 则stackMin压入, 大时, 将栈顶元素重复压入
        // 取数据的时候只需同步取出即可, 较前一个思路比较浪费空间
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }
}
