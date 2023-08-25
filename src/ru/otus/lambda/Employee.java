package ru.otus.lambda;

import lombok.ToString;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;


@ToString
public class Employee {
    private String name;
    private Integer salary;
    private Date workStart;
    public Employee(String name, Integer salary, Date workStart) {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    /*@Override
    public String toString() {
        return this.name + " - " + this.salary + " - " + (new SimpleDateFormat("dd.MM.yyyy").format(this.workStart));
    }*/
}
