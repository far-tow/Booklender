package se.lexicon.G4.Booklender.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.G4.Booklender.model.dto.BookDto;
import se.lexicon.G4.Booklender.model.dto.LoanDto;
import se.lexicon.G4.Booklender.service.LoanService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
@Validated
public class LoanController {
    @Autowired
    LoanService loanService;


    @Operation(summary = "Find Loan by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the loan", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(loanService.findById(id.longValue()));
    }

    @Operation(summary = "Find Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Book", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @GetMapping("/bookId{bookId}")
    public ResponseEntity<List<LoanDto>> findByBookId(@PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(loanService.findByBookId(bookId));
    }

    @Operation(summary = "Find User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @GetMapping("/userId")
    public ResponseEntity<List<LoanDto>> findByUserId(Integer userId) {
        return ResponseEntity.ok(loanService.findByUserId(userId));
    }

    @Operation(summary = "Find by Terminated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Is Terminated", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid operation", content = {@Content})
    })
    @GetMapping("/terminated{concluded}")
    public ResponseEntity<List<LoanDto>> findByTerminated(@PathVariable("concluded") boolean terminated) {
        return ResponseEntity.ok(loanService.findByTerminated());
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }
    @Operation(summary = "To Borrow a Book")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Loan Founded",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example",
                            implementation = LoanDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
    @PostMapping("/")
    public ResponseEntity<LoanDto> create(@RequestBody @Valid LoanDto loanDto){
        LoanDto createdLoanDto = loanService.create(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoanDto);
    }
    @Operation(summary = "To Rebook or update a Loan")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Loan is updated", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid LoanDto loanDto){
        loanService.update(loanDto);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "To Delete a Loan or Borrowed Book")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Loan is deleted", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid Loan id supplied", content = {@Content})
    })
    @DeleteMapping ("/")
    public ResponseEntity<Void> deleteById(@RequestBody @Valid Long id){
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
