package ru.netology.pechenin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Operation {
    private LocalDate date;
    private int clientId;
    private double transactionSum;

    public Operation() {

    }

    private void setDate(String dateScan) {
        LocalDate date = LocalDate.parse(dateScan, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    private void setTransaction_sum(double transactionSum) {
        this.transactionSum = transactionSum;
    }

    public double getTransactionSum() {
        return transactionSum;
    }

    // Метод вывода информации по транзакции в консоль
    public void print() {
        System.out.println("Дата транзакции: " + getDate() + "| ID пользователя: " + getClientId() + "| Сумма: " + getTransactionSum());
    }

    public void setTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите дату транзакции:");
        setDate(scanner.nextLine());

        System.out.println("Укажите id пользователя:");
        setClientId(scanner.nextInt());

        System.out.println("Укажите сумму транзакции:");
        setTransaction_sum(scanner.nextDouble());

        if (getClientId() > Main.arraysLength) {
            throw new CustomerOperationOutOfBoundException(getClientId(), Main.operations.length);
        }
    }
}
