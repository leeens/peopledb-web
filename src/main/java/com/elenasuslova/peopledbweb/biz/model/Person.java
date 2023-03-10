package com.elenasuslova.peopledbweb.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message="Please specify first name")
    private String firstName;

    @NotEmpty(message="Please specify last name")
    private String lastName;

    @Past(message="Please provide a valid date of birth")
    @NotNull(message="Please select date of birth")
    private LocalDate dob;

    @DecimalMin(value="1000.00", message="Salary must be at least 1000")
    @NotNull(message="Please specify a salary")
    private BigDecimal salary;

    @Email(message="Please provide a valid email")
    @NotEmpty(message="Please provide a email")
    private String email;

}
