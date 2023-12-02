package com.example.Expense_Tracker_System.Transformers;

import com.example.Expense_Tracker_System.Models.Expense;
import com.example.Expense_Tracker_System.Models.User;
import com.example.Expense_Tracker_System.RequestDtos.AddExpenseReqDto;

public class ExpenseTransformer {


    public static Expense convertDtoToModel(AddExpenseReqDto reqDto, User user) {
        // Build an Expense object using the provided details and user reference
        Expense obj = Expense.builder()
                .title(reqDto.getTitle())
                .price(reqDto.getPrice())
                .date(reqDto.getDate())
                .description(reqDto.getDescription())
                .user123(user)
                .build();
        return obj;
    }
}
