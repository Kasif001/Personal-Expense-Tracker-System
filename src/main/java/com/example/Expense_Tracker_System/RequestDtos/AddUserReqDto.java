package com.example.Expense_Tracker_System.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddUserReqDto {
    private String name;
    private Integer age;
    private String gender;
    private String email;

}
