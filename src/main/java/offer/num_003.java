package offer;

import util.ListNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class num_003 {
    /**
     * @Author Luckid
     * @Description 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * 输入： {67,0,24,58}  返回值：[58,24,0,67]
     * @Date 11:01 2021/5/31
     **/
    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while(listNode != null){
            list.add((Integer) listNode.data);
            listNode = listNode.next;
        }
        for(int i = list.size() - 1; i > -1; i--){
            ans.add(list.get(i));
        }
        return ans;
    }


    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while(listNode != null){
            stack.push(listNode.data);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("hello");
    }
}
