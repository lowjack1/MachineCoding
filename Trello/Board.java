package com.trello;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.trello.PrivacyMode.PRIVATE;

public class Board implements Deletion{

    private String id;
    private String name;
    private PrivacyMode privacy;
    private String url;
    private Map<String, Lists> list;
    private Map<String, User> members;

    public Board(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.privacy = PRIVATE;
        this.url = "https://www.trello.com/board/" + this.id;
        this.list = new HashMap<>();
        this.members = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrivacyMode getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyMode privacy) {
        this.privacy = privacy;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Lists> getList() {
        return list;
    }

    public void setList(Map<String, Lists> list) {
        this.list = list;
    }

    public Map<String, User> getMembers() {
        return members;
    }

    public void setMembers(Map<String, User> members) {
        this.members = members;
    }


    public void deleteMember(String memberId) {
        if (getMembers().containsKey(memberId)) {
            getMembers().remove(memberId);
        }
    }

    public void addMember(User user) {
        getMembers().put(user.getUserId(), user);
    }

    @Override
    public void delete() {
        for (String listId : getList().keySet()) {
            Lists list = getList().get(listId);
            list.delete();
        }
        setList(null);
        setId("");
        setName("");
        setPrivacy(null);
        setUrl("");
        setMembers(null);
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", privacy=" + privacy +
                ", url='" + url + '\'' +
                ", list=" + list +
                ", members=" + members +
                '}';
    }
}
