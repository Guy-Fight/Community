package love.xuqinqin.community.dto;

import lombok.Data;

/**
 * @Author FGuy
 * @Date 2020/2/26 17:16
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
