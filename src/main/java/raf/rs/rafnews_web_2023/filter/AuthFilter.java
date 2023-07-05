package raf.rs.rafnews_web_2023.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import raf.rs.rafnews_web_2023.model.Comment;
import raf.rs.rafnews_web_2023.model.enumeration.Role;
import raf.rs.rafnews_web_2023.model.enumeration.Status;
import raf.rs.rafnews_web_2023.resource.NewsResource;
import raf.rs.rafnews_web_2023.resource.UserResource;
import raf.rs.rafnews_web_2023.service.CommentService;
import raf.rs.rafnews_web_2023.service.NewsService;
import raf.rs.rafnews_web_2023.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Provider
public class AuthFilter implements ContainerRequestFilter {
    public static final String SECRET_KEY = "Lubenjaja";
    @Inject
    UserService userService;

    @Inject
    NewsService newsService;
    @Inject
    CommentService commentService;


    @Override

    public void filter(ContainerRequestContext requestContext) throws IOException {

        // provera da li ruta zahteva autorizaciju
        if (!this.isAuthRequired(requestContext))
            // zahtev pusten na izvrsavanje
            return;

        // Autorizovane rute zahtevaju potpis potrazioca u naslovu
        String jwt = requestContext.getHeaderString("Authorization");

        // Nije se potpisao trazioc
        if (jwt == null) {
            requestContext.abortWith(
                    Response.status(Response.Status.NETWORK_AUTHENTICATION_REQUIRED).build()
            );
            throw new IOException("NEki neulogovani lik je trazio direktan pristup ruti");
        }

        //jwt konvencija
        if (jwt.startsWith("Bearer"))
            jwt = jwt.replace("Bearer ", "");

        if (isJwtExpired(jwt)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build()
            );
            throw new IOException("Istekao jwt token");
        }

        Map<String, Object> userInformation = unpackJwtPayload(jwt, SECRET_KEY);
        if (userInformation == null) {
            requestContext.abortWith(Response.status(Response.Status.NETWORK_AUTHENTICATION_REQUIRED).build());
            throw new IOException("Neko je prčkao po jwt-u");
        }
        if (!isAccessAllowed(userInformation, requestContext))
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

    }


    private boolean isAuthRequired(ContainerRequestContext request) {
        // login and signup are auth free as an enter-point to application
        String requestedPath = request.getUriInfo().getPath();

        if (requestedPath.endsWith("users/login") || requestedPath.endsWith("users/signup") || requestedPath.contains("users/email/"))
            return false;


        // data editing operations require authorization
        if (request.getMethod().equals("PUT") || request.getMethod().equals("POST") || request.getMethod().equals("DELETE"))
            return true;

        List<Object> matchedResources = request.getUriInfo().getMatchedResources();

        //Users' table data viewing and editing require authorization (admin only)
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof UserResource)
                return true;
        }
        return false;
    }

    public Map<String, Object> unpackJwtPayload(String jwtToken, String secretKey) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        Map<String, Object> claims;
        try {
            DecodedJWT decodedJwt = verifier.verify(jwtToken);
            String email = decodedJwt.getSubject();
            int id = decodedJwt.getClaim("id").asInt();
            String role = decodedJwt.getClaim("role").asString();
            String status = decodedJwt.getClaim("status").asString();

            claims = new HashMap<>();
            claims.put("id", id);
            claims.put("email", email);
            claims.put("role", role);
            claims.put("status", status);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return claims;
    }

    boolean isAccessAllowed(Map<String, Object> userInfo, ContainerRequestContext request) {


        //deaktivirani ljudi ne mogu nicemu da pristupe cemu mogu ulogovani aktivni korisnici
        if (userInfo.get("status").equals(Status.DEACTIVATED.name()))
            return false;

        if (userInfo.get("status").equals(Status.ACTIVE.name()) && userInfo.get("role").equals(Role.ADMIN.name()))
            return true;


        //ako neko trazi da pristupi users resursu ne moze ako nije admin (samo admin ima pristup tabeli usera)
        if (request.getUriInfo().getMatchedResources().get(0) instanceof UserResource)
            return false;


        //autorizacija za novine, brisanje , admin moze da brise sve novine, obican user samo svoje novine
        if (request.getUriInfo().getMatchedResources().get(0) instanceof NewsResource && "DELETE".equals(request.getMethod())) {

            // id novine koji pokusava da obrise
            int newsId = Integer.parseInt(getLastResourcePath(request));

            // lista novina kojima je on autor
            int authorId = newsService.findById((int) userInfo.get("id")).getAuthor().getId();

            //on je pisao novine moze da ih obrise...
            return authorId == (int) userInfo.get("id");

        }
        if (request.getUriInfo().getMatchedResources().get(0) instanceof Comment
                &&
                ("DELETE".equals(request.getMethod()) || "PUT".equals(request.getMethod()))) {

            // id komentar koji pokusava da obrise
            int commentId = Integer.parseInt(getLastResourcePath(request));
            //dovlacim komentar za koji se zahteva brisanje
            Comment comment = commentService.findById(commentId);
            //pitam da li postoji
            if(comment == null)
                return false;
            //on je pisao komentar moze da ih obrise i da menja...
            return comment.getAuthorId() == (int) userInfo.get("id");

        }

        return false;
    }

    private String getLastResourcePath(ContainerRequestContext requestContext) {
        UriInfo uriInfo = requestContext.getUriInfo();
        String path = uriInfo.getPath();

        // Remove leading and trailing slashes, if present
        path = path.replaceAll("^/+", "").replaceAll("/+$", "");

        // Split the path by slashes
        String[] parts = path.split("/");

        // Return the last part
        return parts[parts.length - 1];
    }

    private boolean isJwtExpired(String jwtToken) {
        try {
            DecodedJWT decodedJwt = JWT.decode(jwtToken);
            Date expirationDate = decodedJwt.getExpiresAt();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}

