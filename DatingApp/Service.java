package com.dating.app;

import java.util.*;

public class Service implements operations {

    private static Service object = null;
    Map<String, User> users = new HashMap<>();

    private Service() {

    }

    public static Service getInstance() {
        if (object == null) {
            object = new Service();
        }
        return object;
    }

    public boolean createAccount(String uid, String name, int x, int y, int age, Gender gender) {
        if (name == null || name.trim().length() == 0) {
            System.out.println("Invalid name");
            System.out.println();

            return false;
        }
        if (age < 0) {
            System.out.println("Invalid age");
            System.out.println();

            return false;
        }

        if (!(gender.equals(Gender.FEMALE) || gender.equals(Gender.MALE))) {
            System.out.println("Invalid gender");
            System.out.println();

            return false;
        }

        if (users.containsKey(uid)) {
            System.out.println("User already exists");
            System.out.println();

            return false;
        }
        User user = new User(uid, name, gender, age, new Location(x, y));
        users.put(uid, user);

        return true;
    }

    public void showAllUser() {
        for (String key : users.keySet()) {
            System.out.println(users.get(key).toString());
            System.out.println();

        }
    }

    public boolean like(String userId1, String userId2) {
        if (userId1 == null || userId2 == null || userId1.trim().length() == 0 ||
                userId2.trim().length() == 0 || !users.containsKey(userId1) ||
                !users.containsKey(userId2)) {
            System.out.println("User not registered");
            System.out.println();

            return false;
        }

        User user1 = users.get(userId1);
        User user2 = users.get(userId2);

        user1.getLikes().add(user2);
        user2.getLikedBy().add(user1);

        System.out.println("USERID=" + userId1 + " liked USERID=" + userId2);
        System.out.println();

        if (user1.getLikes().contains(user2) && user2.getLikes().contains(user1)) {
            user1.getMatches().add(user2);
            user2.getMatches().add(user1);
            System.out.println("USERID=" + userId1 + " matched with USERID=" + userId2);
            System.out.println();

        }

        return true;
    }

    public void showMatches(String userId) {
        System.out.println("====USERID=" + userId + " is matched with following users===");
        for (User user : users.get(userId).getMatches()) {
            System.out.println(user.toString());
        }
        System.out.println("========= Matching end=============");
        System.out.println();

    }

    public void showAllMatches() {
        for (User user : users.values()) {
            int size = user.getMatches().size();
            if (size == 0) continue;
            System.out.print(user.getName() + ": ");
            for (User match : user.getMatches()) {
                size--;
                System.out.print(match.getName());
                if (size > 0) System.out.print(", ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void potentialMatches(String uid) {
        List<User> userList = new ArrayList<>();
        User currentUser = users.get(uid);
        for (User user : users.values()) if (!user.equals(currentUser)) {
            userList.add(user);
        }
        Collections.sort(userList, new Relevance());
        int size = userList.size();
        for (User user : userList) {
            size--;
            System.out.print(user.getName());
            if (size > 0) System.out.println(", ");
        }
        System.out.println();
//        System.out.println(userList);
    }

    public void ignore(String uid1, String uid2) {

    }

    public boolean deleteAccount(String uid) {
        if (!users.containsKey(uid)) {
            System.out.println("User does not exist for deletion");
            System.out.println();
            return false;
        }

        User user = users.get(uid);
        for (User currentUser : users.values()) {
            if (currentUser.equals(user)) continue;
            currentUser.getLikedBy().remove(user);
            currentUser.getLikes().remove(user);
            currentUser.getMatches().remove(user);
        }
        users.remove(uid);
        System.out.println("USERID=" + user.getUid() + " is removed\n");

        return true;
    }

    class Relevance implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            if (!user1.getGender().equals(user2.getGender())) return -1;
            int dis = Math.abs(user1.getAge() - user2.getAge());
            if (dis != 0) return dis;
            return getDistance(user1.location, user2.location);
        }

        public int getDistance(Location l1, Location l2) {
            int x = l1.getX() - l2.getX(), y = l1.getY() - l2.getY();
            return x * x + y * y;
        }
    }
}
