package org.example;
import java.time.LocalDate;
public class User {
    private LocalDate dateOfBirth;
    private String department;
    private int income;

    public User(LocalDate dateOfBirth, String department, int income) {
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.income = income;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDepartment() {
        return department;
    }

    public int getIncome() {
        return income;
    }
}
