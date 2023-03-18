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
import java.time.format.DateTimeFormatter;

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

    private String photoFileName;

    public static Person parse(String csvLine) {
        String[] fields = csvLine.split(",\\s*");
        LocalDate dob = LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("M/d/yyyy"));

        return new Person(null, fields[2], fields[4], dob, new BigDecimal(fields[25]), fields[6], null);
    }


}
