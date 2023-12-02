package com.example.Expense_Tracker_System.Controllers;

import com.example.Expense_Tracker_System.CustomExceptions.MonthNumberIsInvalidException;
import com.example.Expense_Tracker_System.CustomExceptions.UserNotFoundException;
import com.example.Expense_Tracker_System.Models.Expense;
import com.example.Expense_Tracker_System.RequestDtos.UpdateExpenseReqDto;
import com.example.Expense_Tracker_System.RequestDtos.AddExpenseReqDto;
import com.example.Expense_Tracker_System.Serivces.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // API to add a new expense
    @PostMapping("/addNewExpense")
    public ResponseEntity<String> addExpense(@RequestBody AddExpenseReqDto expense) {
        try {
            // Call the service method to add a new expense
            String str = expenseService.addNewExpense(expense);
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors and return an internal server error response
            log.error("There is an error in add Expense class", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API to get all expenses for a specific user using path parameter
    @GetMapping("/getAllExpensesById/{id}")
    public ResponseEntity<List<Expense>> getAllExpensesById(@PathVariable Integer id) {
        try {
            // Call the service method to get all expenses for the given user ID
            List<Expense> list = expenseService.getAllExpenseById(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors and return an internal server error response
            log.error("There is an error in getAllExpenses class", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // API to delete an expense by ID using path parameter
    @DeleteMapping("/deleteExpense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer expenseId) {
        try {
            // Call the service method to handle the deletion of the expense
            String str  = expenseService.deleteExpenseById(expenseId);
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors and return an internal server error response
            log.error("There is an error in deleteExpense class", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API to update an expense by ID using path parameter
    @PutMapping("/updateExpense/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable Integer expenseId, @RequestBody UpdateExpenseReqDto updateExpenseReqDto) {
        try {
            // Call the service method to handle the update of the expense
            String str = expenseService.updateExpenseById(expenseId, updateExpenseReqDto);
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors and return an internal server error response
            log.error("There is an error in updateExpense class", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/monthlyExpense/{userId}/{month}")
    public ResponseEntity<?> monthlyExpense(@PathVariable Integer userId,@PathVariable Integer month) {
        try {
            // Call the service method to get all expenses for the given user ID
            List<Expense> list = expenseService.monthlyExpense(userId, month);

            if (list.isEmpty()) {
                // Return a custom message in the response body
                String message = "No expenses found for the month with the given user ID";
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (UserNotFoundException | MonthNumberIsInvalidException e) {
            //catching exceptions
            String errorMessage = "Error processing the request: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String errorMessage = "Error processing the request: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/totalExpenseOfMonth/{userId}/{month}")
    public ResponseEntity<String> totalExpenseOfParticularMonth(@PathVariable Integer userId, @PathVariable Integer month) {
        try {
            // Call the service method to get the total expense for the given user ID and month
            String str = expenseService.totalExpenseOfMonth(userId, month);
            return new ResponseEntity<>(str, HttpStatus.OK);

        } catch (UserNotFoundException | MonthNumberIsInvalidException e) {
            //catching exceptions
            String errorMessage = "Error processing the request: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            //catching exceptions
            String errorMessage = "Error processing the request: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
