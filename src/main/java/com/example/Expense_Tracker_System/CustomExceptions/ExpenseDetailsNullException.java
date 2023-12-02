package com.example.Expense_Tracker_System.CustomExceptions;

public class ExpenseDetailsNullException extends Exception{
    public ExpenseDetailsNullException(String message){
        super(message);
    }
}
