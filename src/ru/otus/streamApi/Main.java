package ru.otus.streamApi;

import ru.otus.lambda.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";
    public static void main(String[] args) {

        ArrayList<Employee> staff = loadStaffFromFile();

        Stream<Employee> stream = staff.stream();

        stream.filter(employee -> employee.getSalary() >= 100000).forEach(System.out::println);

        Stream<Integer> numbers = Stream.of(1,2,3,4,5,6,7,8,9,10);
        numbers.filter(number -> number % 2 == 0).forEach(System.out::println);


        //
        // Integer[] number = {1,2,3,4,5,6,7,8,9,10};
        // Arrays.stream(number).forEach
        // Для использования с многопоточностью
        // staff.parallelStream()

        // Бесконечный Stream
        Stream.iterate(1, n-> n+1).forEach(System.out::println);
        Stream.generate(()-> "aaa").forEach(System.out::println);

        // Stream из char
        // "wefwefwef".chars().filter()

        // Сортировка
        staff.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);


        Optional<Employee> optional = staff.stream().max(Comparator.comparing(Employee::getSalary));
        optional.ifPresent(System.out::println);

        // Stream.of(1,2,3,4);
    }
    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines) {
                String[] fragments = line.split(" ");
                if(fragments.length != 6) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                int year = Integer.parseInt(fragments[3]);
                int month = Integer.parseInt(fragments[4]);
                int day = Integer.parseInt(fragments[5]);
                staff.add(new Employee(fragments[0]+fragments[1],
                        Integer.parseInt(fragments[2]),
                        new Date(year, month, day)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }
}