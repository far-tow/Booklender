package se.lexicon.G4.Booklender.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


// Nivethitha: Added Book Entity
@Entity

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
    }

    public Book(String title, int maxLoanDays, BigDecimal finePerDay, String bookDescription) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.bookDescription = bookDescription;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && available == book.available && reserved == book.reserved && maxLoanDays == book.maxLoanDays && Objects.equals(title, book.title) && Objects.equals(finePerDay, book.finePerDay) && Objects.equals(bookDescription, book.bookDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, maxLoanDays, finePerDay, bookDescription);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", reserved=" + reserved +
                ", maxLoanDays=" + maxLoanDays +
                ", finePerDay=" + finePerDay +
                ", bookDescription='" + bookDescription + '\'' +
                '}';
    }
}
