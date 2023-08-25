package ru.otus.foreach_construction;

import ru.otus.lambda.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";
    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        for(Employee employee : staff) {
            System.out.println(employee);
        }

        // С использованием foreach
        staff.forEach(employee -> System.out.println(employee));
        // С указателем на метод
        // staff.forEach(System.out::println);

        //
        System.out.println("New salaries: ");
        staff.forEach(employee -> {
            int salary = employee.getSalary();
            employee.setSalary(salary + 1000);
        });

        // Короткая запись
        System.out.println("New salaries: ");
        int salaryIncrease = 10000;
        staff.forEach(e -> e.setSalary(e.getSalary() + salaryIncrease));
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