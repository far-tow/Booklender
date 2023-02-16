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
import se.lexicon.G4.Booklender.model.dto.BookDto;
import se.lexicon.G4.Booklender.service.BookService;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@Validated
public class BookController {

    @Autowired
    BookService bookService;

    @Operation(summary = "Finding all books that are reserved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that are reserved", content = {@Content})
    })
    @GetMapping("/{reserved}")
    public ResponseEntity<List<BookDto>> findByReserved(@PathVariable("reserved") boolean reserved) {
        return ResponseEntity.ok(bookService.findByReserved(reserved));
    }

    @Operation(summary = "Finding all books that are available to lend")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that are available", content = {@Content})
    })
    @GetMapping("/{available}")
    public ResponseEntity<List<BookDto>> findByAvailable(@PathVariable("available") boolean available) {
        return ResponseEntity.ok(bookService.findByAvailable(available));
    }

    @Operation(summary = "Finding all books with this title:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that has this title", content = {@Content})
    })
    @GetMapping("/{title}")
    public ResponseEntity<List<BookDto>> findByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @Operation(summary = "Finding this specific book id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book!", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any book with that id", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") int bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @Operation(summary = "Finding all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These are all the books", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books", content = {@Content})
    })
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByAll());
    }

}
