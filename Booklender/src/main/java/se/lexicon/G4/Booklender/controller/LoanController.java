package se.lexicon.G4.Booklender.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.G4.Booklender.model.dto.LoanDto;
import se.lexicon.G4.Booklender.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/vi/loan")
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
    @GetMapping("/userId{userId}")
    public ResponseEntity<List<LoanDto>> findByUserId(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(loanService.findByUserId(userId));
    }

    @Operation(summary = "Find by Terminated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Is Terminated", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid operation", content = {@Content})
    })
    @GetMapping("/terminated{terminated}")
    public ResponseEntity<List<LoanDto>> findByTerminated(@PathVariable("terminated") boolean terminated) {
        return ResponseEntity.ok(loanService.findByTerminated());
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }

}
