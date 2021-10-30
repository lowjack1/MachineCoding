package com.dating.app;

public class Main {
    public static void main(String[] args) {
        Service userService = Service.getInstance();

        userService.createAccount("1", "ankit", 1, 2, 22, Gender.MALE);
        userService.createAccount("2", "ankush", 2, 3, 22, Gender.MALE);
        userService.createAccount("3", "kriti", 4, 3, 22, Gender.FEMALE);
        userService.createAccount("4", "soni", 9, 3, 22, Gender.FEMALE);
        userService.showAllUser();

        userService.like("1", "3");
        userService.like("3", "1");
        userService.like("1", "4");
        userService.like("4", "2");

        userService.showMatches("1");
        userService.showMatches("3");
        userService.showMatches("4");

        userService.showAllMatches();

        userService.potentialMatches("1");
        userService.deleteAccount("3");
        userService.potentialMatches("1");

    }
}
