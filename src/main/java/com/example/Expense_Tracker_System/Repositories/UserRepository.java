package com.example.Expense_Tracker_System.Repositories;

import com.example.Expense_Tracker_System.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
