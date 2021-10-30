package com.trello;
import java.util.*;

public class Lists implements Deletion {
    private String id;
    private String name;
    private Map<String, Card> cards;

    public Lists(String name) {
        this.name = name;
        this.cards = new HashMap<>();
        this.id = UUID.randomUUID().toString();
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

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setCards(Map<String, Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lists lists = (Lists) o;
        return Objects.equals(id, lists.id) && Objects.equals(name, lists.name) && Objects.equals(cards, lists.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cards);
    }

    @Override
    public void delete() {
        for (String cardId : getCards().keySet()) {
            Card card = getCards().get(cardId);
            card.delete();
        }
        setCards(null);
        setName("");
        setId("");
    }
}
