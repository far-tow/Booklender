package se.lexicon.G4.Booklender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.repository.LibraryUserRepository;

import java.time.LocalDate;
@Component
public class AppCommandLineRunner implements CommandLineRunner {

@Autowired
        LibraryUserRepository libraryUserRepository;

    LibraryUser CreatedLibraryUser;
    @Override
    public void run(String... args) throws Exception {
        LibraryUser user1 = new LibraryUser(LocalDate.now(), "Nive", "Nive6@gmail.com");
     //   CreatedLibraryUser = libraryUserRepository.save(user1);

    }
}
