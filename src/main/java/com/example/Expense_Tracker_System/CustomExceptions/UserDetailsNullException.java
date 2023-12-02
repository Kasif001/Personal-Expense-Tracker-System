package com.example.Expense_Tracker_System.CustomExceptions;

public class UserDetailsNullException extends Exception{
    public UserDetailsNullException(String message){
        super(message);
    }
}
