package com.example.notes;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示StringTable的堆空间溢出错误
 *
 * list存放的是引用，并不是静态，所以在栈。
 * 所以溢出一定是String
 * @author 辞
 * @date 2023/5/12 17:41
 * @apiNote
 */
public class StringSpace {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 999999999; i++) {
            String s = "a";
            s = s+i;
            list.add(s);
            int a = 0;
        }
    }

    public static class ListOverflowExample {
        public static void main(String[] args) {
            // 创建一个ArrayList
            List<Integer> list = new ArrayList<>();

            // 无限循环
            while (true) {
                // 向list中添加元素
                list.add(Integer.MAX_VALUE);
                // 打印list的大小
                System.out.println("List size: " + list.size());
            }
        }
    }
}






















