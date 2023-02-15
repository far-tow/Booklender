package se.lexicon.G4.Booklender.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


// Nivethitha: Added Book Entity
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int bookId;
    @Column(nullable = false, length = 100)
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String bookDescription;

    public Book() {
        this.maxLoanDays = 10;
        this.finePerDay = BigDecimal.valueOf(10);
    }

    // Added for Testing
    public Book(String title, String bookDescription) {
        this.title = title;
        this.bookDescription = bookDescription;
        this.available = true;
        this.reserved = false;
    }

    // Todo Corrected
    public Book(String title, int maxLoanDays, BigDecimal finePerDay, String bookDescription) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.bookDescription = bookDescription;
        this.available = true;
        this.reserved = false;
    }

    public Book(int bookId, String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String bookDescription) {
        this.bookId = bookId;
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.bookDescription = bookDescription;
    }


}
