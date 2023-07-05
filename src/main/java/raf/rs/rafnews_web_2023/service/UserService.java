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


    public UserDTO addUser(UserDTO userDTO) {

        User user = UserDTO_Converter.convertToUser(userDTO);
        return UserDTO_Converter.convertToUserDTO(userRepository.addUser(user));
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
        newUser = userRepository.addUser(newUser);
        String jwt = createUserJWT(newUser);
        return new ResponseBuilder().setStatus(200).setMessage("Signup Successful").build();
    }
    public Response login(LoginRequest loginRequest) {

        String hashedPassword = DigestUtils.sha256Hex(loginRequest.getPassword());

        User user = userRepository.searchUserByEmail(loginRequest.getEmail());

        //Korisnik sa ovim mailom nije registrovan...ne postoji acc
        if (user == null)
            return new ResponseBuilder().setStatus(301).setMessage("Authentication failed").build();

        //Da l je uneo tacnu sifru
        if (!user.getHashedPassword().equals(hashedPassword))
            return new ResponseBuilder().setStatus(302).setMessage("Passwords not match").build();

        //nasli coveka i tacna sifra pravim payload za coveka stavjam id, role, status
        String jwt = createUserJWT(user);
        return new ResponseBuilder().setStatus(200).setMessage("ok").setJwt(jwt).build();

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
                .withClaim("firstName", user.getId())
                .withClaim("lastName", user.getId())
                .withClaim("role", user.getRole().name())
                .withClaim("status", user.getStatus().name())
                .sign(algorithm);
    }

}
