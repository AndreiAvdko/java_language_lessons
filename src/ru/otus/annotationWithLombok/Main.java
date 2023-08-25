package ru.otus.annotationWithLombok;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Василий Петров", 75000, new Date());
        System.out.println(employee);
    }
}
