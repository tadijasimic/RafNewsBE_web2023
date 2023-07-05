package raf.rs.rafnews_web_2023.model;

import raf.rs.rafnews_web_2023.model.enumeration.Role;
import raf.rs.rafnews_web_2023.model.enumeration.Status;

public class User {


    private int id;

    private  String email;
    private  String firstName;
    private  String lastName;

    private  String password;

    private  Role role;

    private  Status status;


    public User(int id, String email, String firstName, String lastName, String hashedPassword, String role, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = hashedPassword;
        this.email = email;
        this.role = Role.valueOf(role);
        this.status = Status.valueOf(status);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }
    public Status getStatus() {
        return status;
    }







}
