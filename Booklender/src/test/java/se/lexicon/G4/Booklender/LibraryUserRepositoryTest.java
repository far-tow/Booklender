package se.lexicon.G4.Booklender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;

import java.time.LocalDate;
import java.util.Optional;



@DataJpaTest
public class LibraryUserRepositoryTest {
    @Autowired
    LibraryUserRepository libraryUserRepository;

    LibraryUser CreatedLibraryUser;

    @BeforeEach
    void setUp() {

        LibraryUser user = new LibraryUser(LocalDate.now(), "Nive", "Nive@gmail.com");
        CreatedLibraryUser = libraryUserRepository.save(user);

    }

    @Test
    void getByEmail() {

        Optional<LibraryUser> foundUser = libraryUserRepository.findByEmail(CreatedLibraryUser.getEmail());
        if (foundUser.isPresent())
            Assertions.assertEquals(CreatedLibraryUser, foundUser);
    }


}
