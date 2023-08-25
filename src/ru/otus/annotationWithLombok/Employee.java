package ru.otus.annotationWithLombok;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(exclude = "workStart")
@AllArgsConstructor
public class Employee {
    public  String name;
    private Integer salary;
    private Date workStart;


    public void setName(@NonNull String name) {
        this.name = name;
    }
}
