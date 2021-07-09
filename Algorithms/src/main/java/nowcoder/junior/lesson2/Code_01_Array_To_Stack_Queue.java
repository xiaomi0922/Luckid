package nowcoder.junior.lesson2;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.lang.reflect.Array;
import java.net.Inet4Address;

public class Code_01_Array_To_Stack_Queue {
    /**
     * @Author zhoujie
     * @Description 用数组实现大小固定的队列和栈
     * @Date 14:42 2021/7/8
     */
    // 数组生成栈
    public static class ArrayStack{
        private Integer[] arr;
        private Integer index;

        public ArrayStack(int initSize){
            if(initSize < 0){
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            index = 0;
        }

        public Integer peek(){
            if(index == 0){
                return null;
            }
            return arr[index - 1];
        }

        public void push(int obj){
            if(index == arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            arr[index++] = obj;
        }

        public Integer pop() {
            if (index == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            return arr[--index];
        }
    }

    public static class ArrayQueue{
        private Integer[] arr;
        private Integer index;
        private Integer start;
        private Integer end;

        public ArrayQueue(int initSize){
            if(initSize < 0){
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            index = 0;
            start = 0;
            end = 0;
        }

        public Integer peek(){
            if(index == 0){
                return null;
            }
            return arr[start];
        }

        public void push(int obj){
            if(index == arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            index++;
            arr[end] = obj;
            // 当end数组长度时, 返回到0, 否则end+1
            end = end==arr.length-1 ? 0: end+1;
        }

        public Integer pull(){
            if(index == 0){
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            index--;
            int tmp = start;
            start = start==arr.length-1 ? 0 : start+1;
            return arr[tmp];
        }
    }
}
