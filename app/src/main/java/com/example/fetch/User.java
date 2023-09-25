package com.example.fetch;

public class User implements Comparable<User> {
    private String ID;
    private String listID;
    private String name;
    public User(String ID, String listID, String name) {
        this.ID = ID;
        this.listID = listID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getListID() {
        return listID;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        if (!this.listID.equals(o.listID)) {
            return this.listID.compareTo(o.listID);
        }
        else {
            return this.name.compareTo(o.name);
        }
    }
}
