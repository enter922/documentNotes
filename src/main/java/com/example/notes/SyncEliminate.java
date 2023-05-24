package com.example.notes;

/**
 * 这段代码中，两个方法都是同步的，但是它们没有任何实际的操作，所以同步锁是没有必要的。如果开启了同步消除，
 * 可以通过-XX:+DoEscapeAnalysis -XX:+EliminateLocks参数来运行程序，会发现耗时大大减少。
 * 这就说明JVM在运行期优化了同步锁，提高了性能。
 *
 * @author 辞
 * @date 2023/5/12 20:28
 * @apiNote
 */
public class SyncEliminate {
    private static final int CIRCLE = 2000000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        SyncEliminate syncEliminate = new SyncEliminate();
        Thread thread1 = new Thread(() -> syncEliminate.method1());
        Thread thread2 = new Thread(() -> syncEliminate.method2());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

    public void method1() {
        for (int i = 0; i < CIRCLE; i++) {
            synchronized (this) {
                // do nothing
            }
        }
    }

    public void method2() {
        for (int i = 0; i < CIRCLE; i++) {
            synchronized (this) {
                // do nothing
            }
        }
    }
}
