package com.example.Expense_Tracker_System.Serivces;

import com.example.Expense_Tracker_System.CustomExceptions.UserDetailsNullException;
import com.example.Expense_Tracker_System.Models.User;
import com.example.Expense_Tracker_System.Repositories.UserRepository;
import com.example.Expense_Tracker_System.RequestDtos.AddUserReqDto;
import com.example.Expense_Tracker_System.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public String addUser(AddUserReqDto reqDto) throws Exception {

        // Check if the required user fields are not null
        if (reqDto.getName() == null || reqDto.getAge() == null || reqDto.getEmail() == null || reqDto.getGender() == null) {
            throw new UserDetailsNullException("User Fields should not be null");
        }

        // Convert DTO to User entity
        User user = UserTransformer.convertDtoToUser(reqDto);

        // Save the user in the repository
        userRepository.save(user);

        // Return a success message
        return "This is your user ID " + user.getUserId().toString() + " ,track your expenses using this ID";
    }
}
