package raf.rs.rafnews_web_2023.dto.request;


public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
