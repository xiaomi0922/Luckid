package offer;

import com.sun.tools.javac.util.StringUtils;

public class num_002 {
    /**
     * @Author Luckid
     * @Description
     * 替换空格
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如输入："We Are Happy" 返回值："We%20Are%20Happy"
     * @Date 10:24 2021/5/31
     **/
    public static String replaceSpace1 (String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null || "".equals(s)){
            return s;
        }
        String[] strs = s.split("");
        for(String str: strs){
            if (" ".equals(str)){
                sb.append("%20");
            }
            else{
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String replaceSpace2 (String s) {
        if (s == null || "".equals(s))
            return s;
        return s.replaceAll(" ", "%20");
    }


    public static void main(String[] args) {
        String s = "We Are Happy";
        System.out.println(replaceSpace1(s));
        System.out.println(replaceSpace2(s));
    }
}
