package ru.otus.multithreading.demo1;

/*
Порядок старта потоков случаен и выбирается операционной системой
*/

public class Demo1 {
    public static void main(String[] args) {
        // Можно запустить так
        // HelloThread helloThread = new HelloThread();
        // new Thread(helloThread).start();

        for(int i = 0; i < 10; ++i) {
            new HelloThread().start();

        }
        System.out.println("Hello from main thread");
    }

    private static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from " + getName());
        }
    }

    /* Второй способ создания потока */
    private static class HelloRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from" + Thread.currentThread().getName());
        }
    }
}