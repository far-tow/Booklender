package se.lexicon.G4.Booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByEmail(String email);

    List<LibraryUser> findByRegDate(LocalDate date);


}