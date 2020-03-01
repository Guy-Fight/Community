package love.xuqinqin.community.dto;

import lombok.Data;

/**
 * @Author FGuy
 * @Date 2020/3/1 21:08
 */
@Data
public class PaginationDto {
    private int firstNum = 1;
    private int endNum = 9;
    private int nowNum = 1;
    private int maxPage = 9;
    private Boolean isShowFirstPage = false;
    private Boolean isShowPrevious = false;
    private Boolean isShowNext = true;
    private Boolean isShowEndPage = true;
}
