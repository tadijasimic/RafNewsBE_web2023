package raf.rs.rafnews_web_2023.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import raf.rs.rafnews_web_2023.entity.User;
import raf.rs.rafnews_web_2023.entity.dto.UserDTO;
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
        return buildListDTO(userRepository.allUsers());
    }

    public List<UserDTO> usersForPage(int pageIndex, int pageSize) {
        return buildListDTO(userRepository.allUsers());
    }


    public UserDTO addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getName(),
                userDTO.getSurname(),
                null,
                userDTO.getRole(),
                userDTO.getStatus()
        );
        return userRepository.addUser(user).buildDTO();
    }

    public void deleteUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getName(),
                userDTO.getSurname(),
                null,
                userDTO.getRole(),
                userDTO.getStatus()
        );
        userRepository.deleteUser(user);
    }

    public UserDTO searchUserByEmail(String email) {
        return userRepository.searchUserByEmail(email).buildDTO();
    }

    public UserDTO searchUserById(int id) {
        return userRepository.searchUserById(id).buildDTO();
    }

    public UserDTO editUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getName(),
                userDTO.getSurname(),
                null,
                userDTO.getRole(),
                userDTO.getStatus()
        );
        return userRepository.editUser(user).buildDTO();
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

    private List<UserDTO> buildListDTO(List<User> users) {
        List<UserDTO> DTOs = new ArrayList<>();
        for (User user : users) {
            DTOs.add(user.buildDTO());
        }
        return DTOs;
    }
}
