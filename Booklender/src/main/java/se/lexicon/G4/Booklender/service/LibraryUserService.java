package se.lexicon.G4.Booklender.service;

import se.lexicon.G4.Booklender.model.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(int userId);
    LibraryUserDto findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto libraryUserDto);
    LibraryUserDto update(LibraryUserDto libraryUserDto);
    boolean delete(int userId);

}
