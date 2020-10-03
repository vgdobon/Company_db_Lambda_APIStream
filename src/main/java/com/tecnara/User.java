package com.tecnara;

public class User {

    private int id;
    private final String name;
    private final int age;
    private final float salary;

    public User(String name, int age, float salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public User(int id, String name, int age, float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getSalary() {
        return salary;
    }

}
