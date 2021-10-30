package com.trello;

import java.util.Objects;
import java.util.UUID;

public class User {

    private String userId;
    private String name;
    private String email;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.userId = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email);
    }
}
