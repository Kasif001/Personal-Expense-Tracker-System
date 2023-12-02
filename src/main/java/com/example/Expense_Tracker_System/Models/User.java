package com.example.Expense_Tracker_System.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;
    private Integer age;
    private String gender;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user123",cascade = CascadeType.ALL)
    private List<Expense> expenseList = new ArrayList<>();


}