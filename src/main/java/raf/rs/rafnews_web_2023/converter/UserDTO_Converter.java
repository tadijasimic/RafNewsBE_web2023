package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.AuthorDTO;
import raf.rs.rafnews_web_2023.dto.UserDTO;
import raf.rs.rafnews_web_2023.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class UserDTO_Converter {

    private UserDTO_Converter() {
    }

    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(user);
    }
    public static List<UserDTO> convertToUserDTOList(List<User> users) {
        List<UserDTO> dtoList = new ArrayList<>();
        for(User user : users) {
            dtoList.add(convertToUserDTO(user));
        }
        return dtoList;
    }



    public static AuthorDTO convertToAuthorDTO(User user) {
        return new AuthorDTO(user);
    }

    public static List<AuthorDTO> convertToAuthorDTOList(List<User> users) {
        List<AuthorDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(convertToAuthorDTO(user));
        }
        return dtoList;
    }


        public static User convertToUser(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getEmail(),
                dto.getName(),
                dto.getSurname(),
                null,
                dto.getRole(),
                dto.getStatus());
    }


}
