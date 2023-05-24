package com.example.notes.redis;

import java.io.IOException;
import java.net.Socket;

/**
 * @author 辞
 * @date 2023/5/4 23:03
 * @apiNote
 */
public class Redisclient1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Redis1.start");
        Socket socket = new Socket("127.0.0.1",6379);
        System.out.println("Redis1.over");
    }
}

 class Redisclient2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Redis2.start");
        Socket socket = new Socket("127.0.0.1",6379);
        System.out.println("Redis2.over");
    }
}
