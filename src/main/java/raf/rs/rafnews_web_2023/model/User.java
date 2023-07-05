package raf.rs.rafnews_web_2023.model;

import raf.rs.rafnews_web_2023.model.enumeration.Role;
import raf.rs.rafnews_web_2023.model.enumeration.Status;

public class User {


    private int id;

    private final String email;
    private final String firstName;
    private final String lastName;

    private final String hashedPassword;

    private final Role role;

    private final Status status;



    public User(int id, String email, String firstName, String lastName, String hashedPassword, String role, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
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

    public String getHashedPassword() {
        return hashedPassword;
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
