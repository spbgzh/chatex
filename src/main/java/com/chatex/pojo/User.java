package com.chatex.pojo;

public class User  implements Comparable<User>{
    private int id;
    private String age;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String gender;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }


    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public User(String username, String password, String age, String gender, String first_name, String last_name, String email, String phone_number, String role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }


    @Override
    public int compareTo(User o) {
        return Integer.compare(o.getId(), this.getId());
    }
}
