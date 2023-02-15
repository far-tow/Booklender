package se.lexicon.G4.Booklender.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.G4.Booklender.exception.DataNotFoundException;
import se.lexicon.G4.Booklender.model.dto.LoanDto;
import se.lexicon.G4.Booklender.model.entity.Loan;
import se.lexicon.G4.Booklender.repository.LoanRepository;
import se.lexicon.G4.Booklender.service.LoanService;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    ModelMapper modelMapper;


    @Override
    public LoanDto findById(Long loanId) {
        if (loanId == null) throw new IllegalArgumentException("Id can not be null");
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (!optionalLoan.isPresent()) throw new DataNotFoundException("role id was not found");
        Loan entity = optionalLoan.get();
        return modelMapper.map(entity, LoanDto.class);
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("BookId was null");
        List<Loan> loanList = loanRepository.findAllByBook_BookId(bookId);
        if (loanList.isEmpty()) throw new DataNotFoundException("List was empty");
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>() {
        }.getType());
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("UserId was null");
        List<Loan> loanList = loanRepository.findAllByLoanTaker_UserId(userId);
        if (loanList.isEmpty()) throw new DataNotFoundException("List was empty");

        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>() {
        }.getType());
    }

    @Override
    public List<LoanDto> findByTerminated() {
        List<Loan> loanList = loanRepository.findByTerminated(true);

        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>() {
        }.getType());
    }

    @Override
    public List<LoanDto> findAll() {
        Iterable<Loan> loans = loanRepository.findAll();
        return modelMapper.map(loans, new TypeToken<List<LoanDto>>() {
        }.getType());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public LoanDto create(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("LoanDto was null");
        if (loanDto.getLoanId() != 0) throw new IllegalArgumentException("Loan id should be zero");

        Loan createdEntity = loanRepository.save(modelMapper.map(loanDto, Loan.class));
        return modelMapper.map(createdEntity, LoanDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public LoanDto update(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("LoanDto was null");
        if (loanDto.getLoanId() == 0) throw new IllegalArgumentException("Loan id should not be zero");

        LoanDto foundLoan = findById(loanDto.getLoanId());
        if (foundLoan == null) throw new DataNotFoundException("We could not find that loan to update it");

        Loan loan = loanRepository.save(modelMapper.map(loanDto, Loan.class));
        return modelMapper.map(loan, LoanDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long loanId) {
        if (loanId == null) throw new IllegalArgumentException("LoanId can not be null to delete");
        LoanDto loanDto = findById(loanId);
        if (loanDto == null) throw new DataNotFoundException("LoanId not found");
        loanRepository.deleteById(loanId);

        return true;
    }
}
