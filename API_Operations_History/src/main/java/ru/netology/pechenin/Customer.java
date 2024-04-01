package ru.netology.pechenin;

import java.util.Scanner;

public class Customer {
    private String customerName;
    private int customerAge;

    // Конструктор класса
    public Customer() {

    }

    // Инициализация сеттеров и геттеров
    private void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    private void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }
    public int getCustomerAge() {
        return customerAge;
    }


    public void customerRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("СОЗДАНИЕ КЛИЕНТА:");
        System.out.println("-----------------");
        System.out.println("Введите имя клиента");
        setCustomerName(scanner.nextLine());
        System.out.println("Введите возраст клиента");
        setCustomerAge(scanner.nextInt());
    }
}
