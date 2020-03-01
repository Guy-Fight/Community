package love.xuqinqin.community.dto;

import lombok.Data;
import love.xuqinqin.community.model.User;

/**
 * @Author FGuy
 * @Date 2020/3/1 11:40
 */
@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private User user;

}
