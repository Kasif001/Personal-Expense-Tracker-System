package com.example.Expense_Tracker_System.CustomExceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
