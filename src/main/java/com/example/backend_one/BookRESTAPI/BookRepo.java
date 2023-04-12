package com.example.backend_one.BookRESTAPI;

import java.util.ArrayList;
import java.util.List;

public class BookRepo {
    public List<Book> getBookList() {

        List<Book> bookList = new ArrayList<>();

        Book b1 = new Book(1, "Jayne Eyre", "Charlotte Bronte");
        Book b2 = new Book(2, "Wuthering Heights", "Emily Bronte");
        Book b3 = new Book(3, "Crime and Punishment", "Fjodor Dostovjevsky");
        Book b4 = new Book(4, "Madame Bovary", "Gustave Flaubert");
        Book b5 = new Book(5, "Catcher in the Rye", "J.D. Salinger");
        Book b6 = new Book(6, "Sandman, vol 1", "Neil Gaiman");
        Book b7 = new Book(7, "Pale King", "David Foster Wallace");

        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);
        bookList.add(b7);

        return bookList;

    }
}
