package se.lexicon.G4.Booklender.service;

import se.lexicon.G4.Booklender.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    BookDto findById(int bookId);

    List<BookDto> findByAll();

    BookDto create(BookDto bookDto);


    BookDto update(BookDto bookDto);

    boolean delete(int bookId);


}
