package se.lexicon.G4.Booklender.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

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

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
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


    //Getters and setters

    public Long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }


    //ToString and Equals/Hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return terminated == loan.terminated && Objects.equals(loanId, loan.loanId) && Objects.equals(loanTaker, loan.loanTaker) && Objects.equals(book, loan.book) && Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, terminated);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminated +
                '}';
    }

}
