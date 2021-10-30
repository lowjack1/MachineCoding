package com.dating.app;

public interface operations {
    boolean createAccount(String uid, String name, int x, int y, int age, Gender gender);
    void showAllUser();
    boolean like(String userId1, String userId2);
    void showMatches(String userId);
    void showAllMatches();
    void potentialMatches(String uid);
    void ignore(String uid1, String uid2);
    boolean deleteAccount(String uid);

}
