package com.library.system;

import java.util.Map;

public class User {
    String name;
    int id;
    int maxAllowed;
    Map<Book, String> issuedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public boolean issueBook(Book book, String date) {
        if (maxAllowed == 0 || issuedBooks.containsKey(book)) return false;
        maxAllowed--;
        issuedBooks.put(book, date);
        return true;
    }

    public boolean returnBook(Book book) {
        if (!issuedBooks.containsKey(book)) return false;
        maxAllowed++;
        issuedBooks.remove(book);
        return true;
    }
}
