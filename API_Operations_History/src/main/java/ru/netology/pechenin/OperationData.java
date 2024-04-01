package ru.netology.pechenin;

import java.io.Serializable;

public class OperationData implements Serializable {

    private Operation[] operations;
    private Customer[] customers;
    private int[][] statement;
    private int arraysLength;


    public OperationData(Operation[] operations, Customer[] customers, int[][] statement, int arraysLength) {
        this.operations = operations;
        this.customers = customers;
        this.statement = statement;
        this.arraysLength = arraysLength;
    }

    public Operation[] getOperations() {
        return operations;
    }
}
