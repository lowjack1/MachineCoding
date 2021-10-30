package com.trello;

import java.util.Scanner;

public class Parser {
    Scanner in;

    public Parser() {
        this.in = new Scanner(System.in);
    }

    public String boardCreate() {
        return in.nextLine();
    }
}
