package raf.rs.rafnews_web_2023.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.codec.digest.DigestUtils;
import raf.rs.rafnews_web_2023.converter.UserDTO_Converter;
import raf.rs.rafnews_web_2023.dto.request.LoginRequest;
import raf.rs.rafnews_web_2023.dto.response.Response;
import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.dto.user.UserDTO;
import raf.rs.rafnews_web_2023.model.Comment;
import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.model.User;
import raf.rs.rafnews_web_2023.repository.api.UserRepositoryAPI;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static raf.rs.rafnews_web_2023.dto.response.Response.ResponseBuilder;
import static raf.rs.rafnews_web_2023.filter.AuthFilter.SECRET_KEY;

public class UserService {

    @Inject
    private UserRepositoryAPI userRepository;

    public UserService() {
    }

    public List<UserDTO> allUsers() {
        List<User> users = userRepository.allUsers();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(UserDTO_Converter.convertToUserDTO(user));
        }
        return dtoList;
    }

    public List<UserDTO> usersForPage(int pageIndex, int pageSize) {
        List<User> users = (userRepository.allUsers());
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(UserDTO_Converter.convertToUserDTO(user));
        }
        return dtoList;
    }


    public UserDTO addUser(UserDTO userDTO)  {
        try {
            User user = UserDTO_Converter.convertToUser(userDTO);
            return UserDTO_Converter.convertToUserDTO(userRepository.addUser(user));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(UserDTO userDTO) {

        userRepository.deleteUser(
                UserDTO_Converter.convertToUser(userDTO)
        );

    }

    public UserDTO searchUserByEmail(String email) {
        return UserDTO_Converter.convertToUserDTO(
                userRepository.searchUserByEmail(email)
        );
    }

    public UserDTO searchUserById(int id) {
        return UserDTO_Converter.convertToUserDTO(
                userRepository.searchUserById(id)
        );
    }

    public UserDTO editUser(UserDTO userDTO) {

        return UserDTO_Converter.convertToUserDTO(
                userRepository.editUser(
                        UserDTO_Converter.convertToUser(userDTO)
                )
        );
    }

    public AuthorDTO searchAuthor(News news) {
        return UserDTO_Converter.convertToAuthorDTO(
                userRepository.searchUserById(news.getAuthorId())
        );
    }

    public AuthorDTO searchAuthor(Comment comment) {
        return UserDTO_Converter.convertToAuthorDTO(
                userRepository.searchUserById(comment.getAuthorId())
        );
    }


    public List<AuthorDTO> searchNewsAuthors(List<News> news) {
        List<AuthorDTO> dtoList = new ArrayList<>();
        for (News currNews : news) {
            dtoList.add(searchAuthor(currNews));
        }
        return dtoList;
    }

    public List<AuthorDTO> searchCommentsAuthors(List<Comment> comments) {
        List<AuthorDTO> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            dtoList.add(searchAuthor(comment));
        }
        return dtoList;
    }

    public Response signup(UserDTO signupInfo) {

        User newUser = UserDTO_Converter.convertToUser(signupInfo);

        //hash password before storing
        String passwordHashed = DigestUtils.sha256Hex(newUser.getPassword());
        System.out.println(passwordHashed);
        newUser.setPassword(passwordHashed);
         /*
        jedini slucaj u kom dodavanje ne uspeva je ako je mail vec postojao u bazi pre signup zahteva,
        to baza sama resava setovanjem email-a kao unique key u user tabeli
        */
        try {
            newUser = userRepository.addUser(newUser);
        }
        catch (Exception e) {
            e.printStackTrace();
            String message = "Sorry, the email address you entered is already associated with an existing account. Please use a different email address or try logging in instead.";
            return new ResponseBuilder().setStatus(302).setMessage(message).build();
        }
        String jwt = createUserJWT(newUser);
        return new ResponseBuilder().setStatus(200).setMessage("Signup Successful").setJwt(jwt).build();
    }

    public Response login(LoginRequest loginRequest) {
        //hashamo sifru
        String hashedPassword = DigestUtils.sha256Hex(loginRequest.getPassword());

        //trazim usera sa mailom iz login requesta
        User user = userRepository.searchUserByEmail(loginRequest.getEmail());

        //Korisnik sa ovim mailom nije registrovan...ne postoji acc
        if (user == null) {
            String message = "There is no registered account associated with this email. Please enter a valid email address or sign up to create a new account.";
            return new ResponseBuilder().setStatus(301).setMessage(message).build();
        }
        //Da l je uneo tacnu sifru
        if (!user.getPassword().equals(hashedPassword)) {
            String message = "The password you entered is incorrect. Please verify your password and try again.";
            return new ResponseBuilder().setStatus(302).setMessage(message).build();
        }
        //nasli coveka i tacna sifra pravim payload za coveka stavjam id, role, status
        String jwt = createUserJWT(user);
        Response loginResponse = new ResponseBuilder().setStatus(200).setMessage("ok").setJwt(jwt).build();
        System.out.println(loginResponse);
        return loginResponse;
    }


    private String createUserJWT(User user) {

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .withClaim("role", user.getRole().name())
                .withClaim("status", user.getStatus().name())
                .sign(algorithm);
    }

}
