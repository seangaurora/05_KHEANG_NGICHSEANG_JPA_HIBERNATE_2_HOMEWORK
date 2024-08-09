package com.example._05_kheang_kheangngichseang_jpa_hibernate_2_homework;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<APIResponse<Book>> insertBook(@RequestBody BookRequest book) {
        Book addedBook = bookRepository.insertBook(book);
        return ResponseEntity.ok(new APIResponse<>(
                "The book is inserted successfully",
                HttpStatus.CREATED,
                201,
                addedBook
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Book>> getBook(@PathVariable UUID id) {
        Book book = bookRepository.getBookById(id);
        return ResponseEntity.ok(new APIResponse<>(
                "The book with id = " + id + " is fetched successfully",
                HttpStatus.OK,
                200,
                book
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Book>> updateBook(@PathVariable UUID id, @RequestBody BookRequest book) {
        Book updatedBook = bookRepository.updateBookById(id, book);
        return ResponseEntity.ok(new APIResponse<>(
                "The book with id = " + id + " is updated successfully",
                HttpStatus.OK,
                200,
                updatedBook
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) {
        bookRepository.deleteBookById(id);
        return ResponseEntity.ok(new APIResponse<>(
                "The book with id = " + id + " is deleted successfully",
                HttpStatus.OK,
                200,
                null
        ));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Book>>> getAllBooks() {
        List<Book> bookList = bookRepository.getAllBooks();
        return ResponseEntity.ok(new APIResponse<>(
                "All books are fetched successfully",
                HttpStatus.OK,
                200,
                bookList
        ));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<APIResponse<List<Book>>> getBooksByTitle(@PathVariable String title) {
        List<Book> bookList = bookRepository.getBooksByTitle(title);
        return ResponseEntity.ok(new APIResponse<>(
                "Found books with title " + title + " are fetched successfully",
                HttpStatus.OK,
                200,
                bookList
        ));
    }


}
