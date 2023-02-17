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
import se.lexicon.G4.Booklender.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Validated
public class BookController {

    @Autowired
    BookService bookService;

    @Operation(summary = "Finding all books that are reserved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that are reserved", content = {@Content})
    })
    @GetMapping("/reserved/{reserved}")
    public ResponseEntity<List<BookDto>> findByReserved(@PathVariable("reserved") boolean reserved) {
        return ResponseEntity.ok(bookService.findByReserved(reserved));
    }

    @Operation(summary = "Finding all books that are available to lend")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that are available", content = {@Content})
    })
    @GetMapping("/available/{available}")
    public ResponseEntity<List<BookDto>> findByAvailable(@PathVariable("available") boolean available) {
        return ResponseEntity.ok(bookService.findByAvailable(available));
    }

    @Operation(summary = "Finding all books with this title:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "These books are found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any books that has this title", content = {@Content})
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDto>> findByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @Operation(summary = "Finding this specific book id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book!", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Could not find any book with that id", content = {@Content})
    })
    @GetMapping("/id/{id}")
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

    @Operation(summary = "To create a Book object")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Book Founded",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example",
                            implementation = BookDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
    @PostMapping("/")
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto bookDto){
        BookDto createdBookDto = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDto);
    }


    @Operation(summary = "To Update a Book")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Book is updated", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid BookDto bookDto){
        bookService.update(bookDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "To Delete a Book")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Book is deleted", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid Book id supplied", content = {@Content})
    })
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") @Valid Integer id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
