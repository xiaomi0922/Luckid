package nowcoder.junior.lesson2;


import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.HashMap;

public class Code_04_RandomPool {
    /**
     * @Author zhoujie
     * @Description 设计一种结构，在该结构中有如下三个功能：
     * insert(key)：将某个key加入到该结构，做到不重复加入。
     * delete(key)：将原本在结构中的某个key移除。
     * getRandom()：等概率随机返回结构中的任何一个key。
     * 【要求】
     * Insert、delete和getRandom方法的时间复杂度都是O(1)。
     * @Date 17:42 2021/7/19
     */
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> IndexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<K, Integer>();
            this.IndexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        public void insert(K key) {
            // 如果keyIndex中不包含key的话, 则添加key元素
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.IndexKeyMap.put(this.size, key);
                size++;
            }
        }

        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                // 得到要删除key的index
                int deleteIndex = this.keyIndexMap.get(key);
                // 得到最后一个元素
                int lastIndex = --this.size;
                K lastKey = this.IndexKeyMap.get(lastIndex);
                // 将最后一个元素替换到删除元素的位置上, 只是改变了map中(key, value)的value值, key没发生变化
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.IndexKeyMap.put(deleteIndex, lastKey);
                // 删除要删除的元素
                this.keyIndexMap.remove(key);
                this.IndexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.IndexKeyMap.get(randomIndex);
        }
    }


    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("zhong");
        pool.insert("hua");
        pool.insert("ren");
        pool.insert("min");
        pool.insert("gong");
        pool.insert("he");
        pool.insert("guo");

        pool.delete("cheng");

        System.out.println(pool.getRandom());
    }
}
