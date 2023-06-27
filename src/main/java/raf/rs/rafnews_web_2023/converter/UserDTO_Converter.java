package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.UserDTO;
import raf.rs.rafnews_web_2023.model.entity.User;

public abstract class UserDTO_Converter {

    private  UserDTO_Converter(){}
    public static UserDTO convertToUserDTO(User user) {
    return new UserDTO(user);
    }
    public static UserDTO convertToAuthorDTO(User user) {
        return new UserDTO(user);
    }

}
