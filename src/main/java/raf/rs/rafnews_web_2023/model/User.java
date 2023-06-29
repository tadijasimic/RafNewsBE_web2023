package raf.rs.rafnews_web_2023.model;

import raf.rs.rafnews_web_2023.dto.UserDTO;
import raf.rs.rafnews_web_2023.model.enumeration.Role;
import raf.rs.rafnews_web_2023.model.enumeration.Status;

public class User {


    private int id;

    private String email;
    private String name;
    private String surname;

    private String hashedPassword;

    private Role role;

    private Status status;



    public User(int id, String email, String name, String surname, String hashedPassword, String role, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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



    public String getName() {
        return name;
    }



    public String getHashedPassword() {
        return hashedPassword;
    }


    public String getSurname() {
        return surname;
    }


    public Role getRole() {
        return role;
    }
    public Status getStatus() {
        return status;
    }






}
