package com.library.system;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Rack {
    int booksCount;
    Map<String, Book> books;
    String id;

    public Rack(int booksCount) {
        this.booksCount = booksCount;
        this.id = UUID.randomUUID().toString();
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.id) || booksCount == 0) return false;
        this.books.put(book.id, book);
        booksCount--;
        return true;
    }

    public Book getBook(String bookid) {
        booksCount++;
        return books.get(bookid);
    }
}
