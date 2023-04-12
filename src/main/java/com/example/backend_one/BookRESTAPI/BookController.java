package com.example.backend_one.BookRESTAPI;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    BookRepo bookRepo = new BookRepo();
    List<Book> bookList = bookRepo.getBookList();
    //denna objekt kommer i praktik vara statisk

    //hämta ut alla böcker:
    @RequestMapping("/books")
    public List<Book> getAllBooks() {
        return bookList;
    }

    //hämta ut 1 book
    @RequestMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }


    @RequestMapping("/books/{fromid}/{toid}")
    public List<Book> getBookByIdRange(@PathVariable int toid, @PathVariable int fromid) {
        return bookList.stream().filter(b -> b.getId() >= fromid && b.getId() <= toid).toList();
    }

    //ta bort en bok
    @RequestMapping("/books/{id}/delete")
    public List<Book> deleteBookById(@PathVariable int id) {
        //removeIf kan man använda direkt på listor för att ändra dem
        bookList.removeIf(b -> b.getId() == id);
        return bookList;
    }

    @RequestMapping("/books/addByGET")
    public List<Book> addByGET(@RequestParam int id,
                               @RequestParam String title, @RequestParam String author) {
        bookList.add(new Book(id, title, author));
        return bookList;
    }

    @PostMapping("/books/add")
    public List<Book> addBookByPOST(@RequestBody Book b) {
        bookList.add(b);
        return bookList;
    }

    @PutMapping("/books/update")
    public List<Book> updateBook(@RequestBody Book b) {
        Book bookToUpdate = bookList.stream()
                .filter(book -> book.getId() == b.getId()).findFirst().orElse(null);
        if (bookToUpdate == null) {
            bookList.add(b);
        } else {
            bookToUpdate.setTitle(b.getTitle());
            bookToUpdate.setAuthor(b.getAuthor());
        }
        return bookList;
    }

}
