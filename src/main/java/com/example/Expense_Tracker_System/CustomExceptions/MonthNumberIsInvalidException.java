package com.example.Expense_Tracker_System.CustomExceptions;

public class MonthNumberIsInvalidException extends Exception{
    public MonthNumberIsInvalidException(String message){
        super(message);
    }
}
