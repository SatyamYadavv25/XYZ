package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // (b) Add a new book using @PostMapping and @RequestBody
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // (c1) Retrieve all books using @GetMapping
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    // (c2) Retrieve a specific book by bookId using @GetMapping
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable int bookId) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found with ID: " + bookId, HttpStatus.NOT_FOUND);
    }

    // (d) Update an existing book's details using @PutMapping
    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable int bookId, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setPrice(updatedBook.getPrice());
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found with ID: " + bookId, HttpStatus.NOT_FOUND);
    }

    // (e) Delete a book by ID using @DeleteMapping
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookId) {
                bookList.remove(i);
                return new ResponseEntity<>("Book deleted successfully with ID: " + bookId, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found with ID: " + bookId, HttpStatus.NOT_FOUND);
    }
}
