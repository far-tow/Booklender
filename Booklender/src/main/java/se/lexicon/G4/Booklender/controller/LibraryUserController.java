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
import se.lexicon.G4.Booklender.model.dto.LibraryUserDto;
import se.lexicon.G4.Booklender.model.entity.LibraryUser;
import se.lexicon.G4.Booklender.service.LibraryUserService;

import javax.validation.Valid;
import java.util.List;

// Methods updated by Nivethitha

@RestController
@RequestMapping("/api/v1/library_user")
@Validated
public class LibraryUserController {

    @Autowired
    LibraryUserService libraryUserService;

    @Operation(summary = "Fetch Library User by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Library User Found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
 @GetMapping("/id/{id}")

    public ResponseEntity<LibraryUserDto> findById(@PathVariable ("id") Integer id)
    {

        return ResponseEntity.ok(libraryUserService.findById(id));
    }
    @Operation(summary = "Fetch Library User by email")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Library User Found", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })

    @GetMapping("/email/{email}")

    public ResponseEntity<LibraryUserDto> findByEmail(@PathVariable ("email") String email)
    {

        return ResponseEntity.ok(libraryUserService.findByEmail(email));
    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findAll());
    }

    @Operation(summary = "To create Library User")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Library User Found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example",
                            implementation = LibraryUserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
    @PostMapping("/")
    public ResponseEntity<LibraryUserDto> create(@RequestBody @Valid LibraryUserDto libraryUserDto){
        LibraryUserDto createdLibraryUserDto = libraryUserService.create(libraryUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibraryUserDto);
    }


    @Operation(summary = "Update Library User")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Library User updated", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })


    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid LibraryUserDto libraryUserDto){
        libraryUserService.update(libraryUserDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Library User")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Library User deleted", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })

    @DeleteMapping ("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        libraryUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
