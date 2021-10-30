package com.trello;

import java.util.Objects;

public class Card implements Deletion{

    private String id;
    private String name;
    private String description;
    private User assignedUser;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(name, card.name) && Objects.equals(description, card.description) && Objects.equals(assignedUser, card.assignedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, assignedUser);
    }

    @Override
    public void delete() {
        setDescription("");
        setId("");
        setAssignedUser(null);
        setAssignedUser(null);
    }
}
