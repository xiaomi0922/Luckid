package nowcoder.junior.lesson2;

import sun.awt.image.ImageWatched;

import javax.naming.InsufficientResourcesException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Code_03_StackAndQueueConvert {
    /**
     * @Author zhoujie
     * @Description 队列实现栈 vs 栈实现队列
     * @Date 16:02 2021/7/8
     */
    public static class TwoQueuesStack {
        // 思路是：再准备一个队列, 每次弹出时将前面得数压入第二个队列,
        // 将最后一个数弹出, 下一次继续压入另外一个队列, 弹出, 循环操作
        private Queue<Integer> data;
        private Queue<Integer> help;

        public TwoQueuesStack() {
            data = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        public void push(int num) {
            data.add(num);
        }

        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() != 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        public int peek() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() != 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        // 引用互换, 之前的data栈作为help栈, help栈作为data栈
        private void swap() {
            Queue<Integer> tmp = help;
            help = data;
            data = tmp;
        }
    }


    // 思路：准备两个栈, 一个push栈, 一个pop栈, push栈只存入数据, pop栈只弹出数据
    // 遵循的原则，一是当pop栈为空时, 才能往pop栈倒数据, 二是如果倒就一次性倒光
    public static class TwoStackQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public void push(int num) {
            stackPush.push(num);
        }

        public int poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            // 首先判断pop栈是否为空, 当为空时, 则将push栈中所有数据一次压入pop栈中
            else if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public int peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }
}
