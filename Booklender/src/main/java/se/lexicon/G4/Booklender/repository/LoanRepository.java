package se.lexicon.G4.Booklender.repository;


import org.springframework.data.repository.CrudRepository;
import se.lexicon.G4.Booklender.model.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByLoanTakerUserId(int userId);
    List<Loan> findByBookBookId(int bookId);
    List<Loan> findByTerminated(boolean terminated);
}