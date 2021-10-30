package com.library.system;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Library {
    private static Library library;
    private int rackCount;
    int bookcount;
    Queue<Rack> racks;
    Map<String, Book> bookids;
    Map<String, Book> bookauthors;
    Map<String, Book> booktitles;
    Map<String, Book> bookpublishers;

    private Library(int rackCount, int bookcount) {
        this.rackCount = rackCount;
        this.bookcount = bookcount;
        this.racks = new LinkedList<>();
        this.bookids = new HashMap<>();
        this.booktitles = new HashMap<>();
        this.bookauthors = new HashMap<>();
        this.bookpublishers = new HashMap<>();
        addRacks();
    }

    public static Library getInstance(int rackCount, int bookcount) {
        if (library == null) {
            library = new Library(rackCount, bookcount);
        }
        return library;
    }

    public void addRacks() {
        for (int i = 1; i <= this.bookcount; i++) racks.offer(new Rack(bookcount));
    }

    public boolean addBook(Book book) {
        int size = racks.size();
        while (size--> 0) {
            Rack curRack = racks.poll();
            if (curRack.addBook(book)) {
                for (String authors : book.getAuthorNames()) {
                    bookauthors.put(authors, book);
                }
                for (String publisher : book.getPublishers()) {
                    bookpublishers.put(publisher, book);
                }
                booktitles.put(book.getName(), book);
                bookids.put(book.getId(), book);
                return true;
            }
        }
        return false;
    }


}
