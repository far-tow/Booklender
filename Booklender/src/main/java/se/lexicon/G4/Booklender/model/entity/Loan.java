package se.lexicon.G4.Booklender.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Data
@Entity
public class Loan {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private LibraryUser loanTaker;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private LocalDate loanDate;
    @Column(name = "concluded")
    private boolean terminated;


    //Constructors

    public Loan() {
    }

    public Loan(Long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanId = loanId;
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = false;
    }

    // Methods

    public boolean isOverdue() {
        LocalDate dueDate = loanDate.plusDays(book.getMaxLoanDays());
        return LocalDate.now().isAfter(dueDate);

    }

    public BigDecimal getFine() {
        Period time_between_loan = Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());
        int overdueTime = time_between_loan.getDays();
        BigDecimal fine = BigDecimal.ZERO;
        if (overdueTime > 0) {
            fine = BigDecimal.valueOf(overdueTime * book.getFinePerDay().floatValue());

        }

        return fine;


    }

    public boolean extendLoans(LocalDate days) {
        if (book.isReserved() || isOverdue()) {
            return false;
        }

        this.loanDate = LocalDate.now().plusDays(book.getMaxLoanDays());

        return true;
    }

    public void setBook(Book book) {
        if (book.isAvailable()) {
            this.book = book;
            book.setAvailable(false);
        }else throw new IllegalArgumentException("Book is already loaned by other");

    }


}
