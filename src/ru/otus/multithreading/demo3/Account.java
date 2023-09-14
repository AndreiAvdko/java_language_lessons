package ru.otus.multithreading.demo3;

public class Account {
    private long balance;
    public Account() {
        this(0L);
    }
    public Account(long balance) {
        this.balance = balance;
    }
    public long getBalance() {
        return balance;
    }


    // Для корректной работы необходимо
    // сделать public synchronized
    public void deposit(long amount) {
        checkAmountNonNegative(amount);
        balance += amount;
    }

    // Для корректной работы необходимо
    // сделать public synchronized
    public void withdraw(long amount) {
        checkAmountNonNegative(amount);
        if (balance < amount) {
            throw new IllegalArgumentException("not enough money");
        }
        balance -= amount;
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }
}
