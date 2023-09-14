package ru.otus.threads;

// 2 интерфейса для потоков
// Runnable и Collable
public class MyThread implements Runnable {
    // 1. Наследуем интерфейс

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Thread thread = new Thread(()-> {});
        thread.start();
        thread.interrupt();
    }
}
