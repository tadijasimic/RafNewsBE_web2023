package raf.rs.rafnews_web_2023.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import raf.rs.rafnews_web_2023.entity.User;
import raf.rs.rafnews_web_2023.repository.api.UserRepositoryAPI;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    private UserRepositoryAPI userRepository;

    public UserService() {
        System.out.println(this);
    }

    public List<User> getAllUsers() {
        return userRepository.allUsers();
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    public User searchUserByEmail(String email) {
        return userRepository.searchUserByEmail(email);
    }

    public User searchUserById(int id) {
        return userRepository.searchUserById(id);
    }

    public User editUser(User user) {
        return userRepository.editUser(user);
    }

    public String login(String username, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = userRepository.searchUserByEmail(username);
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
                .withSubject(username)
                .withClaim("role", user.getRole())
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
