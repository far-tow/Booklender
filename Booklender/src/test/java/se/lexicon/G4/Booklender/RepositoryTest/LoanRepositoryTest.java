package se.lexicon.G4.Booklender.RepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.G4.Booklender.model.entity.Book;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.model.entity.Loan;
import se.lexicon.G4.Booklender.repository.BookRepository;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;
import se.lexicon.G4.Booklender.repository.LoanRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
public class LoanRepositoryTest {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @BeforeEach
    public void setup() {
        BigDecimal bigDecimal = new BigDecimal(10);


        LibraryUser libraryUser1 = new LibraryUser(LocalDate.now(), "Samuel", "samuel.ball@hotmail.com");
        LibraryUser libraryUser2 = new LibraryUser(LocalDate.now(), "Sanna", "sanna@hotmail.com");

        libraryUserRepository.save(libraryUser1);
        libraryUserRepository.save(libraryUser2);


        Book book1 = new Book("Sagan om ringen", "The story about middle earth");
        Book book2 = new Book("Narnia", "Discover the world of Narnia");
        bookRepository.save(book1);
        bookRepository.save(book2);

        Loan loan1 = new Loan(libraryUser1, book1, LocalDate.now());
        Loan loan2 = new Loan(libraryUser2, book2, LocalDate.now());
        loanRepository.save(loan1);
        loanRepository.save(loan2);


    }

    @Test
    public void findByTerminated() {

        List<Loan> optionalList = loanRepository.findByTerminated(true);
        assertNotNull(optionalList);


    }

    @Test
    public void findByBookId() {
        Optional<Loan> expected = loanRepository.findByBookBookId(1);
        assertNotNull(expected);

    }

    @Test
    public void findByLoanTakerUserId() {
        Optional<Loan> expected = loanRepository.findByLoanTakerUserId(1);
        assertNotNull(expected);

    }

}
