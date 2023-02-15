package se.lexicon.G4.Booklender.model.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity

public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @CreationTimestamp
    private LocalDate regDate;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;

    public LibraryUser(LocalDate regDate, String userName, String email) {
        this.regDate = regDate;
        this.userName = userName;
        this.email = email;
    }

    public LibraryUser(int userId, LocalDate regDate, String userName, String email) {
        this.userId = userId;
        this.regDate = regDate;
        this.userName = userName;
        this.email = email;
    }

    public LibraryUser() {

    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryUser)) return false;
        LibraryUser that = (LibraryUser) o;
        return getUserId() == that.getUserId() && Objects.equals(getRegDate(), that.getRegDate()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getRegDate(), getUserName(), getEmail());
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "userId=" + userId +
                ", regDate=" + regDate +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
