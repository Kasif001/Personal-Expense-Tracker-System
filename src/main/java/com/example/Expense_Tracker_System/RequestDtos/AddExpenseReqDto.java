package com.example.Expense_Tracker_System.RequestDtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddExpenseReqDto {

    private String title;
    private Integer price;
    private LocalDate date;
    private String description;
    private Integer yourUserId;
}
