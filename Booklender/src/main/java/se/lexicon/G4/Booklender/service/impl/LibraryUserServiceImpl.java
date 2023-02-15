package se.lexicon.G4.Booklender.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.lexicon.G4.Booklender.exception.DataNotFoundException;
import se.lexicon.G4.Booklender.model.dto.LibraryUserDto;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;
import se.lexicon.G4.Booklender.service.LibraryUserService;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;


@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LibraryUserDto findById(int userId) {
        if(userId == 0)throw new IllegalArgumentException("UserId was zero");
        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(userId);
        if(optionalLibraryUser.isPresent()){
            LibraryUser entity = optionalLibraryUser.get();
            return modelMapper.map(entity, LibraryUserDto.class);
        }
        return null;
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        if(email == null)throw new IllegalArgumentException("User email was zero");
        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findByEmail(email);
        if(optionalLibraryUser.isPresent()){
            LibraryUser entity = optionalLibraryUser.get();
            return modelMapper.map(entity, LibraryUserDto.class);
        }
        return null;
    }

    @Override
    public List<LibraryUserDto> findAll() {
        Iterable<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        return modelMapper.map(libraryUserList, new TypeToken<List<LibraryUserDto>>(){}.getType());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if(libraryUserDto == null) throw  new IllegalArgumentException("Library User was null");
        if(libraryUserDto.getUserId()== 0) throw new IllegalArgumentException("Library User should not be zero");

        LibraryUser createdLibraryUser = libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
        return modelMapper.map(createdLibraryUser, LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {
        if(libraryUserDto == null) throw new IllegalArgumentException("LibraryUser can not be null");
        if(libraryUserDto.getUserId()== 0)throw new IllegalArgumentException("LibraryUserI Id can not be null");

        LibraryUserDto findLibraryUser = findById(libraryUserDto.getUserId());
        if(findLibraryUser == null)throw new DataNotFoundException("Data not found");

        LibraryUser updateLibraryUser = LibraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
        return modelMapper.map(updateLibraryUser, LibraryUserDto.class);
    }

    @Override
    public boolean delete(int userId) {
        if(userId == 0) throw new IllegalArgumentException("The UserId is null");

        LibraryUserDto findLibraryUser = findById(userId);
        if(findLibraryUser == null) throw new DataNotFoundException("Data not found");
        libraryUserRepository.deleteById(userId);
        return true;
    }
}
