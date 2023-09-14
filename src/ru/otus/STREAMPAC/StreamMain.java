package ru.otus.STREAMPAC;

import com.sun.org.apache.xpath.internal.operations.Lt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        List<String> listSrt = new ArrayList<>();

        listSrt.add("Паша");
        listSrt.add("Cаша");
        listSrt.add("Маша");
        listSrt.add("Даша");

        List<String> result = new ArrayList<>();

        for (String name: listSrt) {
            if (name.equals("Маша")) {
                result.add(name);
            }
        }

        // Список преобразовывается в конвеер
        // Predicate - функциональный интерфейс
        // Функциональный интерфейс - интерфейс, содержащий один метод без дефолтной реализации
        // @FunctionalInterface
        // map - преобразователь интерфейса
        listSrt.stream()
                .filter((String name)-> name.equals("Маша")) // промежуточный оператор
                .map((String name) -> new People(name))// промежуточный оператор
                .collect(Collectors.toList()); // терминаторный оператор

        // Терминальный интерфейс forEach()

        // Параллельное выполнение
        listSrt.parallelStream()
                .filter((String name)-> name.equals("Маша")) // промежуточный оператор
                .map((String name) -> new People(name))// промежуточный оператор
                .collect(Collectors.toList()); // терминаторный оператор
    }
}
