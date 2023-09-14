package ru.otus.multithreading.demo3;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        // Пример RACE CONDITION
        // Из-за того, что операции инкремента и декремента
        // в классе Account не атомарны
        // Значение на счёте будет всегда отличаться

        Account account = new Account(100_000);
        System.out.println("Begin balance = " + account.getBalance());

        Thread withdrawThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);
        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        System.out.println("End balance = " + account.getBalance());
    }

    private static class WithdrawThread extends Thread {
        private final Account account;
        private WithdrawThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000; ++i) {
                account.withdraw(1);
            }
        }
    }

    private static class DepositThread extends Thread {
        private final Account account;
        private DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000; ++i) {
                account.deposit(1);
            }
        }
    }
}
