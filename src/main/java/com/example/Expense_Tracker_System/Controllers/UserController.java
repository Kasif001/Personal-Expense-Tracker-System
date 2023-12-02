package com.example.Expense_Tracker_System.Controllers;

import com.example.Expense_Tracker_System.RequestDtos.AddUserReqDto;
import com.example.Expense_Tracker_System.Serivces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // API to add a new user
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody AddUserReqDto reqDto) {
        try {
            // Call the service method to add a new user
            String str = userService.addUser(reqDto);
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors and return an expectation failed response
            log.error("There is an error in add User class", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
