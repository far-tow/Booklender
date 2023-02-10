package se.lexicon.G4.Booklender.RepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.G4.Booklender.model.entity.Book;
import se.lexicon.G4.Booklender.repository.BookRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository testObject;
    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book("Java", 30, BigDecimal.valueOf(10), "programming");
        testObject.save(testBook);
    }

    @Test
    public void findByAvailableStatus() {
        int expectedSize = 1;
        List<Book> result = testObject.findByAvailable(testBook.isAvailable());
        assertEquals(expectedSize, result.size());
    }

    @Test
    public void findByReservedStatus() {
        testBook.setReserved(true);
        int expectedSize = 1;
        List<Book> result = testObject.findByReserved(testBook.isReserved());
        assertEquals(expectedSize, result.size());
    }
}
