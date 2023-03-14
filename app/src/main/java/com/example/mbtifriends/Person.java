package com.example.mbtifriends;

public class Person {
    String first_name;
    String last_name;
    String type;
    String gender;
    String id;

    public Person(String first_name, String last_name, String type, String gender, String id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
        this.gender = gender;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
