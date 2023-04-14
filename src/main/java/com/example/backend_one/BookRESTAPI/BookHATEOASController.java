package com.example.backend_one.BookRESTAPI;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookHATEOASController {
    BookRepo bookRepo = new BookRepo();
    List<Book> bookList = bookRepo.getBookList();


    @GetMapping("/booksHATEOAS/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {

        Book book = bookList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);

        return EntityModel.of(book,
                linkTo(methodOn(BookHATEOASController.class).one(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
    }

    @GetMapping("/booksHATEOAS")
    public CollectionModel<EntityModel<Book>> all() {

        List<EntityModel<Book>> books = bookList.stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookHATEOASController.class).one((long)book.getId())).withSelfRel(),
                        linkTo(methodOn(BookHATEOASController.class).all()).withRel("books"))).toList();

        return CollectionModel.of(books, linkTo(methodOn(BookHATEOASController.class).all()).withSelfRel());
    }
}
