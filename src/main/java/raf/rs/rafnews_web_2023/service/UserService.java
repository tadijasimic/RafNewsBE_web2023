package raf.rs.rafnews_web_2023.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import raf.rs.rafnews_web_2023.converter.UserDTO_Converter;
import raf.rs.rafnews_web_2023.dto.AuthorDTO;
import raf.rs.rafnews_web_2023.dto.UserDTO;
import raf.rs.rafnews_web_2023.model.Comment;
import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.model.User;
import raf.rs.rafnews_web_2023.repository.api.UserRepositoryAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            dtoList.add(
                    UserDTO_Converter.convertToAuthorDTO(
                            userRepository.searchUserById(currNews.getAuthorId())
                    )
            );
        }
        return dtoList;
    }

    public List<AuthorDTO> searchCommentsAuthors(List<Comment> comments) {
        List<AuthorDTO> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            dtoList.add(
                    UserDTO_Converter.convertToAuthorDTO(
                            userRepository.searchUserById(comment.getAuthorId())
                    )
            );
        }
        return dtoList;
    }



    public String login(String email, String password) {

        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = userRepository.searchUserByEmail(email);
        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("role", user.getRole().name())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
//        jwt.getClaim("role").asString();

        /*User user = this.userRepository.findUser(username);

        if (user == null){
            return false;
        }
    */
        return true;
    }

}
