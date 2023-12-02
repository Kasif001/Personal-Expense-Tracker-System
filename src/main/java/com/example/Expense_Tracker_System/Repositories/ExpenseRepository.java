package com.example.Expense_Tracker_System.Repositories;

import com.example.Expense_Tracker_System.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
}
