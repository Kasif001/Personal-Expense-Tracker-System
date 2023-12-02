package com.example.Expense_Tracker_System.Serivces;

import com.example.Expense_Tracker_System.CustomExceptions.ExpenseDetailsNullException;
import com.example.Expense_Tracker_System.CustomExceptions.ExpenseNotFoundException;
import com.example.Expense_Tracker_System.CustomExceptions.MonthNumberIsInvalidException;
import com.example.Expense_Tracker_System.CustomExceptions.UserNotFoundException;
import com.example.Expense_Tracker_System.Models.Expense;
import com.example.Expense_Tracker_System.Models.User;
import com.example.Expense_Tracker_System.Repositories.ExpenseRepository;
import com.example.Expense_Tracker_System.Repositories.UserRepository;
import com.example.Expense_Tracker_System.RequestDtos.UpdateExpenseReqDto;
import com.example.Expense_Tracker_System.RequestDtos.AddExpenseReqDto;
import com.example.Expense_Tracker_System.Transformers.ExpenseTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service class for handling expense-related operations or business logic.
 */
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;


    //add new user function
    @Transactional
    public String addNewExpense(AddExpenseReqDto expense) throws Exception {

        // Validate that essential expense details are provided
        if (expense.getYourUserId() == null || expense.getTitle() == null || expense.getPrice() == null || expense.getDate() == null) {
            throw new ExpenseDetailsNullException("Expense details should not be null");
        }

        // Check if the associated user exists
        if (!userRepository.existsById(expense.getYourUserId())) {
            throw new UserNotFoundException("User ID is invalid");
        }

        // Retrieve the user based on the provided ID
        User user = userRepository.getReferenceById(expense.getYourUserId());

        // Transform DTO to Expense model
        Expense expenseObj = ExpenseTransformer.convertDtoToModel(expense, user);

        // Add the expense to the user's list of expenses
        List<Expense> list = user.getExpenseList();
        list.add(expenseObj);
        user.setExpenseList(list);
        userRepository.save(user);

        // Save the expense to the repository
        expenseRepository.save(expenseObj);

        // Return success message
        return "Expense " + expense.getTitle() + " with ID-" + expenseObj.getId() + " added successfully";
    }



    //Get all your expenses function
    public List<Expense> getAllExpenseById(Integer userId) throws Exception{

        // Retrieve all expenses from the repository
        List<Expense> expenseList = expenseRepository.findAll();

        // Create a list to store expenses associated with the specified user
        List<Expense> myExpense = new ArrayList<>();

        // Iterate through all expenses to filter those belonging to the specified user
        for (Expense exp : expenseList) {
            if (Objects.equals(exp.getUser123().getUserId(), userId)) {
                myExpense.add(exp);
            }
        }

        // Return the list of expenses associated with the specified user
        return myExpense;
    }


     // Delete Particular expense function
    public String deleteExpenseById(Integer expenseId) throws Exception {
        // Check if the expense with the given ID exists
        if (!expenseRepository.existsById(expenseId)) {
            throw new ExpenseNotFoundException("Expense ID is invalid");
        }

        // Retrieve the title of the expense to be deleted
        String title = expenseRepository.getReferenceById(expenseId).getTitle();

        // Delete the expense by its ID
        expenseRepository.deleteById(expenseId);

        // Return a success message
        return "Expense " + title + " Id- " + expenseId + " deleted successfully";
    }


    public String updateExpenseById(Integer expenseId, UpdateExpenseReqDto updateExpenseReqDto) throws Exception {
        // Check if the expense with the given ID exists
        if (!expenseRepository.existsById(expenseId)) {
            throw new ExpenseNotFoundException("Expense ID is invalid");
        }

        // Get the existing expense
        Expense expenseObj = expenseRepository.getReferenceById(expenseId);

        // Update only if the corresponding field in the DTO is not null
        if (updateExpenseReqDto.getTitle() != null) {
            expenseObj.setTitle(updateExpenseReqDto.getTitle());
        }
        if (updateExpenseReqDto.getDescription() != null) {
            expenseObj.setDescription(updateExpenseReqDto.getDescription());
        }
        if (updateExpenseReqDto.getDate() != null) {
            expenseObj.setDate(updateExpenseReqDto.getDate());
        }
        if (updateExpenseReqDto.getPrice() != null) {
            expenseObj.setPrice(updateExpenseReqDto.getPrice());
        }

        // update the expense
        expenseRepository.save(expenseObj);

        // Return a success message
        return "Expense "+updateExpenseReqDto.getTitle()+" updated successfully";
    }

    public List<Expense> monthlyExpense(Integer userId , Integer monthNumber) throws Exception{

        if(monthNumber < 1 || monthNumber > 12){
            throw new MonthNumberIsInvalidException("There is error in provided month number");
        }
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("User ID is invalid");
        }
        // Retrieve all expenses from the repository
        List<Expense> expenseList = expenseRepository.findAll();

        // Create a list to store expenses associated with the specified user
        List<Expense> myExpense = new ArrayList<>();

        // Iterate through all expenses to filter those belonging to the specified user
        for (Expense exp : expenseList) {
            if (Objects.equals(exp.getUser123().getUserId(), userId) && exp.getDate().getMonth().getValue() == monthNumber) {
                myExpense.add(exp);
            }
        }

        // Return the list of expenses associated with the specified user
        return myExpense;
    }


    public String totalExpenseOfMonth(Integer userId , Integer monthNumber) throws Exception{

        // validations
        if(monthNumber < 1 || monthNumber > 12){
            throw new MonthNumberIsInvalidException("There is error in provided month number");
        }
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("User ID is invalid");
        }

        // Retrieve all expenses from the repository
        List<Expense> expenseList = expenseRepository.findAll();

        // Create a list to store expenses associated with the specified user
        int totalExpense = 0;


        // Iterate through all expenses to filter those belonging to the specified user
        for (Expense exp : expenseList) {
            if (Objects.equals(exp.getUser123().getUserId(), userId) && exp.getDate().getMonth().getValue() == monthNumber) {
                totalExpense += exp.getPrice();
            }
        }

        // Return the list of expenses associated with the specified user
        return "Total Expense of this month is "+totalExpense+" ";
    }



}
