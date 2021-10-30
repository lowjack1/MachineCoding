package com.trello;

import java.util.HashMap;
import java.util.Map;

public class Service {
    Map<String, Board> boards;
    Map<String, User> users;

    public Service() {
        boards = new HashMap<>();
        users = new HashMap<>();
    }

    public Map<String, Board> getBoards() {
        return boards;
    }

    public void setBoards(Map<String, Board> boards) {
        this.boards = boards;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public void addBoard(Board board) {
        getBoards().put(board.getId(), board);
    }

    public void deleteBoard(String boardId) {
        Board board = getBoards().get(boardId);
        board.delete();
        getBoards().remove(boardId);
    }

    public void show() {
        if (getBoards().size() == 0) {
            System.out.println("No boards");
            return;
        }
        for (String boardId : getBoards().keySet()) {
            Board board = getBoards().get(boardId);
            System.out.println(board.toString());
        }
    }
}
