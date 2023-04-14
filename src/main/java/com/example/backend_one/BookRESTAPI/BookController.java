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

    //visa bestämda böcker
    @RequestMapping("/books/{fromid}/{toid}")
    public List<Book> getBookByIdRange(@PathVariable int toId, @PathVariable int fromId) {
        return bookList.stream().filter(b -> b.getId() >= fromId && b.getId() <= toId).toList();
    }

    //ta bort en bok
    @RequestMapping("/books/{id}/delete")
    public List<Book> deleteBookById(@PathVariable int id) {
        //removeIf kan man använda direkt på listor för att ändra dem
        bookList.removeIf(b -> b.getId() == id);
        return bookList;
    }

    //Parameter kan ta in mellanrum
    //http://localhost:8080/books/addByGET?id=8&title=Persuasion&author=Jane%20Austen
    @RequestMapping("/books/addByGET")
    public List<Book> addByGET(@RequestParam int id,
                               @RequestParam String title, @RequestParam String author) {
        bookList.add(new Book(id, title, author));
        return bookList;
    }

    //curl http://localhost:8080/books/add -H "Content-Type:application/json" -d "{\"id\":9, \"title\":\"Pippi Langstrump\", \"author\":\"Astrid Lindgren\"}" -v
    @PostMapping("/books/add")
    public List<Book> addBookByPOST(@RequestBody Book b) {
        bookList.add(b);
        return bookList;
    }

    //curl -X PUT http://localhost:8080/books/update -H "Content-Type:application/json" -d "{\"id\":10, \"title\":\"Sense and Sensibility\", \"author\":\"Jane Austen\"}" -v
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
