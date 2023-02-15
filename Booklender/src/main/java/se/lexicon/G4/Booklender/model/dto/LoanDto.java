package se.lexicon.G4.Booklender.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.G4.Booklender.model.entity.Book;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean terminate;
}
