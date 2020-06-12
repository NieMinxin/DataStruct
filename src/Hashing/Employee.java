package Hashing;

import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private  int seniority;
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && name.equals(((Employee) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
