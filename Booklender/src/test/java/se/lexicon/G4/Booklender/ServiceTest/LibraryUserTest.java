package se.lexicon.G4.Booklender.ServiceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.G4.Booklender.model.dto.LibraryUserDto;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;
import se.lexicon.G4.Booklender.service.LibraryUserService;
import se.lexicon.G4.Booklender.service.impl.LibraryUserServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Transactional


public class LibraryUserTest {

    @Autowired

    LibraryUserRepository libraryUserRepository;

    LibraryUser CreatedLibraryUser, CreatedLibraryUser1;


    @TestConfiguration
    static class LibraryUserServiceTestContextConfiguration {

        @Bean
        public LibraryUserService libraryUserService() {
            return new LibraryUserServiceImpl();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    LibraryUserService libraryUserService;
    @Autowired
    ModelMapper modelMapper;

    List<LibraryUser> user;


    @BeforeEach
    void setUp() {

        LibraryUser user1 = new LibraryUser(LocalDate.now(), "Nive", "Test@gmail.com");
        CreatedLibraryUser = libraryUserRepository.save(user1);


        LibraryUser user2 = new LibraryUser(LocalDate.now(), "Jay", "Jay@gmail.com");
        CreatedLibraryUser1 = libraryUserRepository.save(user2);

        user = new ArrayList<>();
        user.add(CreatedLibraryUser);
        user.add(CreatedLibraryUser1);

    }

    @Test
    public void AllUsers() {

        List<LibraryUserDto> foundLibraryUser = libraryUserService.findAll();
        Assertions.assertEquals(modelMapper.map(user, new TypeToken<List<LibraryUserDto>>() {
                }.getType()),
                foundLibraryUser);

    }

    @Test
    public void create() {

        LibraryUserDto foundLibraryUser = libraryUserService.create(modelMapper.map(CreatedLibraryUser, LibraryUserDto.class));
        Assertions.assertEquals(modelMapper.map(CreatedLibraryUser, LibraryUserDto.class),
                foundLibraryUser);

    }

    @Test
    public void update() {
        CreatedLibraryUser.setName("Anika");

        LibraryUserDto foundLibraryUser = libraryUserService.update(modelMapper.map(CreatedLibraryUser, LibraryUserDto.class));
        LibraryUserDto user = modelMapper.map(CreatedLibraryUser, LibraryUserDto.class);
        Assertions.assertEquals(user.getName(),
                foundLibraryUser.getName());
    }
    @Test
    public void delete()
    {


        libraryUserService.delete(CreatedLibraryUser.getUserId());
        LibraryUserDto user = modelMapper.map(CreatedLibraryUser, LibraryUserDto.class);
        Assertions.assertEquals(libraryUserService.findById(CreatedLibraryUser.getUserId()),
                null);

    }

    @Test
    public void findByid() {

        LibraryUserDto foundLibraryUser = libraryUserService.findById(CreatedLibraryUser.getUserId());
        Assertions.assertEquals(modelMapper.map(CreatedLibraryUser, LibraryUserDto.class),
                foundLibraryUser);

    }

    @Test
    public void findByEmail() {

        LibraryUserDto foundLibraryUser = libraryUserService.findByEmail(CreatedLibraryUser.getEmail());
        Assertions.assertEquals(modelMapper.map(CreatedLibraryUser, LibraryUserDto.class),
                foundLibraryUser);

    }



}
