package com.example.Expense_Tracker_System.RequestDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateExpenseReqDto {
    private String title;
    private Integer price;
    private LocalDate date;
    private String description;

}
