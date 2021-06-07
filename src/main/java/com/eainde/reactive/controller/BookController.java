package com.eainde.reactive.controller;

import com.eainde.reactive.entity.Book;
import com.eainde.reactive.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    BookController(final BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("")
    public Flux<Book> getBooks(){
        return bookRepository.findAll();
    }

    @PostMapping("")
    public Mono<Book> saveBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("")
    public Mono<Book> updateBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    public boolean deleteBook(@RequestBody Book book){
        try{
            bookRepository.deleteById(book.getId()).block();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
