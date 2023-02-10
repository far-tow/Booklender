package se.lexicon.G4.Booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByEmail(String email);
}