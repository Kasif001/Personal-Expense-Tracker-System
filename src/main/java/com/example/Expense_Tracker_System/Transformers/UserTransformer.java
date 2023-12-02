package com.example.Expense_Tracker_System.Transformers;

import com.example.Expense_Tracker_System.Models.User;
import com.example.Expense_Tracker_System.RequestDtos.AddUserReqDto;

public class UserTransformer {

    public static User convertDtoToUser(AddUserReqDto reqDto){
        // Build a User object using the provided details
        return User.builder()
                .name(reqDto.getName())
                .age(reqDto.getAge())
                .email(reqDto.getEmail())
                .gender(reqDto.getGender())
                .build();
    }
}
