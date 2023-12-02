package com.example.Expense_Tracker_System.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private Integer price;
    private LocalDate date;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user123;
}
