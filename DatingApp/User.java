package com.dating.app;

import java.util.*;

public class User {
    String uid;
    String name;
    Gender gender;
    int age;
    Location location;
    Set<User> likes;
    Set<User> likedBy;
    Set<User> matches;

    public User(String uid, String name, Gender gender, int age, Location location) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.likes = new HashSet<>();
        this.matches = new HashSet<>();
        this.likedBy = new HashSet<>();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<User> getMatches() {
        return matches;
    }

    public void setMatches(Set<User> matches) {
        this.matches = matches;
    }

    public Set<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(uid, user.uid) && Objects.equals(name, user.name) && gender == user.gender && Objects.equals(location, user.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, gender, age, location);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", location=" + location.toString() +
                '}';
    }
}
