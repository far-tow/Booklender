package se.lexicon.G4.Booklender.service.impl;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.G4.Booklender.model.dto.BookDto;
import se.lexicon.G4.Booklender.model.entity.Book;
import se.lexicon.G4.Booklender.repository.BookRepository;
import se.lexicon.G4.Booklender.service.BookService;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BookDto> findByReserved(boolean reserved) {

        List<Book> bookByReserved = bookRepository.findByReserved(reserved);
        return modelMapper.map(bookByReserved, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {

        List<Book> bookByAvailable = bookRepository.findByAvailable(available);
        return modelMapper.map(bookByAvailable, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if (title.equals(null)) throw new IllegalArgumentException("Title is Null");

        List<Book> bookByTitle = bookRepository.findByTitle(title);
        return modelMapper.map(bookByTitle, new TypeToken<List<BookDto>>() {
        }.getType());

    }

    @Override
    public BookDto findById(int bookId) {
        BookDto foundBook = null;

        if (bookId == 0) throw new IllegalArgumentException("book id cannot be null");

        Optional<Book> findByBook = bookRepository.findById(bookId);

        if (findByBook.isPresent())
            foundBook = modelMapper.map(findByBook.get(), BookDto.class);

        return foundBook;
    }

    @Override
    public List<BookDto> findByAll() {
        Iterable<Book> AllBook = bookRepository.findAll();
        return modelMapper.map(AllBook, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if(bookDto == null) throw new IllegalArgumentException("book cannot be null");
        if(bookDto.)


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
