package com.example.Expense_Tracker_System.CustomExceptions;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException(String message){
        super(message);
    }
}
