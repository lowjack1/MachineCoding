package com.library.system;

import java.util.List;

public class Book {
    String name;
    List<String> authorNames;
    List<String> publishers;
    String id;

    public Book(String name, List<String> authorNames, List<String> publishers, String id) {
        this.name = name;
        this.authorNames = authorNames;
        this.publishers = publishers;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
