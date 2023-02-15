package se.lexicon.G4.Booklender.service.impl;

import se.lexicon.G4.Booklender.model.dto.BookDto;
import se.lexicon.G4.Booklender.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return null;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return null;
    }

    @Override
    public BookDto findById(int bookId) {
        return null;
    }

    @Override
    public List<BookDto> findByAll() {
        return null;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;
    }

    @Override
    public boolean delete(int bookId) {
        return false;
    }
}
