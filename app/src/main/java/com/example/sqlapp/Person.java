package com.example.sqlapp;

public class Person {

    private String name;
    private int age;
    private String occupation;
    private String address;

    // Конструктор
    public Person(String name, int age, String occupation, String address) {

        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.address = address;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
