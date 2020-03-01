package love.xuqinqin.community.model;

import lombok.Data;

/**
 * @Author FGuy
 * @Date 2020/2/27 15:08
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatar_url;

}
