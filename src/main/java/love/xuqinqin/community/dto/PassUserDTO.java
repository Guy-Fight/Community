package love.xuqinqin.community.dto;

import lombok.Data;
import love.xuqinqin.community.model.User;

/**
 * @Author FGuy
 * @Date 2020/3/6 16:02
 */
@Data
public class PassUserDTO {

    int id;
    String username;
    String password;
    User user;

}
