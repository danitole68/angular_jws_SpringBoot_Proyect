package com.prueba.PruebaSpring.controller.rest;

import com.prueba.PruebaSpring.models.Book;
import com.prueba.PruebaSpring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createOrUpdateBook(@RequestBody Book book) {
        if (book.getId() == null) {
            Optional<Book> existingBook = bookService.findByTitle(book.getTitle());
            if (existingBook.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El título ya existe. No se puede agregar el libro.");
            }
            Book newBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } else {
            Optional<Book> existingBook = bookService.findByTitle(book.getTitle());
            if (existingBook.isPresent() && !existingBook.get().getId().equals(book.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El título ya pertenece a otro libro.");
            }
            return ResponseEntity.ok(bookService.updateBook(book.getId(), book));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
