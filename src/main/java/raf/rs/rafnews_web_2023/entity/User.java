package raf.rs.rafnews_web_2023.entity;

public class User {

    public static final String CONTENT_CREATOR_ROLE = "content_creator";
    public static final String ADMIN_ROLE = "admin";
    private int id;

    private String email;
    private String name;
    private String surname;

    private String hashedPassword;

    private String role;

    private String status;

    public User() {}

    public User(String email, String name, String surname,String hashedPassword, String role, String status) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.hashedPassword = hashedPassword;
        this.role = role;
        this.status = status;
    }

    public User(int id, String email, String name, String surname, String hashedPassword, String role, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.role = role;
        this.status = status;
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





    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }


    public String getHashedPassword() {
        return hashedPassword;
    }


    public User setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }


}
