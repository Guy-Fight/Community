package love.xuqinqin.community.service;

import love.xuqinqin.community.dto.PaginationDto;
import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.mapper.PublishMapper;
import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.Publish;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author FGuy
 * @Date 2020/3/1 11:38
 */
@Service
public class QuestionService {

    int Page = 1;

    int dbcount = 0;
    @Value("${myconfig.pagination.maxNum}")
    int maxNum;
    //单数
    int showCount = 5;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> getQuestionDto(int Page){
        this.Page = Page;
        this.dbcount = publishMapper.select().size();
        List<Publish> publishes = publishMapper.selectLimit((this.showPage(Page) - 1) * maxNum,maxNum);
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        if(publishes == null || publishes.size() == 0){
            return null;
        }
        for (Publish publish : publishes) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(publish,questionDto);
//            questionDto.setGmt_create(new Date().getTime() - publish.getGmt_create());
            questionDto.setUser(userMapper.SelectByAccountId(String.valueOf(publish.getCreator())));
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public List<QuestionDto> getMyQuestionDto(int Creator,int Page){
        this.Page = Page;
        this.dbcount = publishMapper.selectByCreator(Creator).size();
        List<Publish> publishes = publishMapper.selectLimitByCreator(Creator,(this.showPage(Page)-1)*8,maxNum);
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        if(publishes == null || publishes.size() == 0){
            return null;
        }
        for (Publish publish : publishes) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(publish,questionDto);
//            questionDto.setGmt_create(new Date().getTime() - publish.getGmt_create());
            questionDto.setUser(userMapper.SelectByAccountId(String.valueOf(publish.getCreator())));
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public PaginationDto getPaginationDto(){
        int nowPage = this.showPage(Page);
        int maxPage = 0;
        if(dbcount%maxNum != 0){
            maxPage = dbcount/maxNum + 1;
        }else{
            maxPage = dbcount/maxNum;
            if(maxPage == 0){
                maxPage = 1;
            }
        }
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setNowNum(nowPage);
        paginationDto.setMaxPage(maxPage);

        if(nowPage >= 2){
            paginationDto.setIsShowPrevious(true);
        }
        if(nowPage >= 3){
            paginationDto.setIsShowFirstPage(true);
        }
        if(maxPage-nowPage < 1){
            paginationDto.setIsShowEndPage(false);
            paginationDto.setIsShowNext(false);
        }
        if(maxPage-nowPage < 2){
            paginationDto.setIsShowEndPage(false);
        }

        int mid = (showCount + 1)/2;

        if(maxPage < showCount){
            paginationDto.setFirstNum(1);
            paginationDto.setEndNum(maxPage);
        }else{
           if(nowPage < mid){
               paginationDto.setFirstNum(1);
               paginationDto.setEndNum(showCount);
           }else if(nowPage < (maxPage - mid + 2)){
               paginationDto.setFirstNum(nowPage - mid + 1);
               paginationDto.setEndNum(nowPage + mid - 1);
           }else{
               paginationDto.setFirstNum(maxPage-(showCount - 1));
               paginationDto.setEndNum(maxPage);
           }
        }

        return paginationDto;
    }

    public int showPage(int page){
        int showPage = page;
        int maxPage = 0;
        if(dbcount%maxNum != 0){
            maxPage = dbcount/maxNum + 1;
        }else{
            maxPage = dbcount/maxNum;
            if(maxPage == 0){
                maxPage = 1;
                showPage = 1;
            }
        }
        if(showPage <= 0){
            showPage = 1;
        }else if(showPage > maxPage){
            showPage = maxPage;
        }
        return showPage;
    }

}
