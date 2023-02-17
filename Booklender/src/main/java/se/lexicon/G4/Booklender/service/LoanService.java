package se.lexicon.G4.Booklender.service;

import se.lexicon.G4.Booklender.model.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(Long loanId);

    List<LoanDto> findByBookId(int bookId);

    List<LoanDto> findByUserId(int userId);

    List<LoanDto> findByTerminated(boolean terminated);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(Long loanId);
}
