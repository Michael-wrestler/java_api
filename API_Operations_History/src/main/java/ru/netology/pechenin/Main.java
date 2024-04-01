package ru.netology.pechenin;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    protected static Operation[] operations;
    protected static Customer[] customers;
    protected static int[][] statement;
    public static int input;
    static int arraysLength = 10;
    static Path data = Paths.get("C:\\Users\\Michel\\java_api\\serialize.ser");

    public static void main(String[] args) {
        operations = new Operation[arraysLength];
        customers = new Customer[arraysLength];
        statement = new int[arraysLength][];

        try (OutputStream fileOutputStream = Files.newOutputStream(data)) {
            OperationData operationData = new OperationData(operations, customers, statement, arraysLength);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(operationData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream fileInputStream = Files.newInputStream(data)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            OperationData operationData = (OperationData) objectInputStream.readObject();

            System.out.println(operationData);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(true){
            mainMenu();
            if (input == 1) {
                createCustomer();
            } else if(input == 2) {
                createOperation();
            } else if(input == 3) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Укажите ID транзакции: ");
                int id = scanner.nextInt();
                operations[id].print();
            } else if (input == 4) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Укажите ID пользователя: ");
                int id = scanner.nextInt();
                getOperations(id);
            } else {
                break;
            }
        }
    }

    // Создание операции
    public static void createOperation() {
        for (int i = 0; i < operations.length; i++) {
            if (operations[i] == null) {
                operations[i] = new Operation();
                operations[i].setTransaction();       // вызываем метод создания транзакции
                int clientId = operations[i].getClientId();      // получаем id клиента, чтобы положить в индекс массива statement
                for (int j = 1; j<operations.length; j++) {
                    if (statement[clientId] == null) {      // значит по клиенту не было создано массива хранения операций
                        statement[clientId] = new int[operations.length];       // Берём всю длину operations, т.к. все операции могут быть у одного клиента
                        statement[clientId][0] = i;     // Кладём id операции в нулевой индекс, т.к. по клиенту ещё не было операций
                    } else if (statement[clientId][j] == 0) {       // Зайдёт сюда только если по клиенту была хоть одна операция
                        statement[clientId][j] = i;
                    }
                }
            } else {
                continue;
            } break;        // Завершает цикл for когда ввели данные по operations[i]
        }
    }

    // Создание пользователя
    public static void createCustomer() {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                continue;
            } else {
                customers[i] = new Customer();
                customers[i].customerRegistration(); // Вызываем метод создания пользователя из класса Customer
                System.out.println("Создан пользователь " + customers[i].getCustomerName() + " | " + customers[i].getCustomerAge() + " лет | ID: " + i);
            } break;
        }
    }


    // Выводит информацию по операциям клиента в консоль
    public static Operation[] getOperations(int clientId) {
        int[] operationId = new int[statement[clientId].length];
        for (int i = 0; i<statement[clientId].length; i++) {
            operationId[i] = statement[clientId][i];
            System.out.println(operationId[i]);
        }
        Operation[] clientOperations = new Operation[statement[clientId].length];
        for (int i = 0; i<clientOperations.length; i++) {
            clientOperations[i] = operations[operationId[i]];       // Получаем в clientOperations[] данные об i-той транзакции
            clientOperations[i].print();
        }
        return clientOperations;
    }

    public static void mainMenu() {
        System.out.println("ВЫБЕРИТЕ ПУНКТ МЕНЮ: ");
        System.out.println("----------------------");
        System.out.println("1) Создание пользователя");
        System.out.println("2) Ввод данных по транзакции");
        System.out.println("3) Получение данных по транзакции");
        System.out.println("4) Получение информации об операциях клиента");
        System.out.println("----------------------");

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
    }
}
