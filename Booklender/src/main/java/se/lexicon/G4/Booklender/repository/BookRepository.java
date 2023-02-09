package se.lexicon.G4.Booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.G4.Booklender.model.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByReserved(boolean status);

    List<Book> findByAvailable(boolean status);

    List<Book> findByTitle(String title);

}
