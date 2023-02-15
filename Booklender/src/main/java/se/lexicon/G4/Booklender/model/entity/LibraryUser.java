package se.lexicon.G4.Booklender.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity

public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @CreationTimestamp
    private LocalDate regDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    public LibraryUser(LocalDate regDate, String userName, String email) {
        this.regDate = regDate;
        this.name = userName;
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

}
