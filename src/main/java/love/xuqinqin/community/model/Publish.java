package love.xuqinqin.community.model;

import lombok.Data;

/**
 * @Author FGuy
 * @Date 2020/2/28 20:05
 */
@Data
public class Publish {

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

}
