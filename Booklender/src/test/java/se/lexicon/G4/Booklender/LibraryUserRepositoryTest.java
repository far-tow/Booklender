package se.lexicon.G4.Booklender;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Transactional
public class LibraryUserRepositoryTest {
    @Autowired
    LibraryUserRepository libraryUserRepository;

    LibraryUser CreatedLibraryUser;

    @BeforeEach
    void setUp() {

        LibraryUser user1 = new LibraryUser(LocalDate.now(), "Nive", "Test@gmail.com");
        CreatedLibraryUser = libraryUserRepository.save(user1);

    }

    @Test
    void getByEmail() {

        Optional<LibraryUser> foundUser = libraryUserRepository.findByEmail(CreatedLibraryUser.getEmail());


        if (foundUser.isPresent()){
            assertEquals(CreatedLibraryUser.getUserId(), foundUser.get().getUserId());
        assertEquals(CreatedLibraryUser.getRegDate(), foundUser.get().getRegDate());
        assertEquals(CreatedLibraryUser.getUserName(), foundUser.get().getUserName());
        assertEquals(CreatedLibraryUser.getEmail(), foundUser.get().getEmail());

        assertEquals(CreatedLibraryUser, foundUser.get());
    }}


    @Test
    void getByRegistrationDate() {

        List<LibraryUser> user1 = libraryUserRepository.findByRegDate(CreatedLibraryUser.getRegDate());

        assertEquals(CreatedLibraryUser, user1.get(0));
    }

}
