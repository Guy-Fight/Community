package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.PaginationDto;
import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.model.User;
import love.xuqinqin.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author FGuy
 * @Date 2020/3/2 14:32
 */
@Controller
public class myPublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/myPublish/{action}")
    public String myPublish(@PathVariable(name="action")String action, @RequestParam(name="page",required = false)String page, HttpServletRequest request, Model model){
        if("question".equals(action)){
            int IntPage = 1;
            try{
                IntPage = Integer.valueOf(page);
            }catch (Exception e){
            //e.printStackTrace();
            }

            if(request.getSession().getAttribute("user") == null){
                return "redirect:/";
            }


            int Creator = ((User)request.getSession().getAttribute("user")).getId();

            List<QuestionDto> myQuestionDto = questionService.getMyQuestionDto(Creator,IntPage);
            PaginationDto paginationDto = questionService.getPaginationDto();
            model.addAttribute("myQuestionDto",myQuestionDto);
            model.addAttribute("pagination",paginationDto);
            model.addAttribute("title","我的发起");
        }else if("replies".equals(action)){
            PaginationDto paginationDto = new PaginationDto();
            paginationDto.setFirstNum(1);
            paginationDto.setEndNum(1);
            paginationDto.setMaxPage(1);
            paginationDto.setNowNum(1);
            paginationDto.setIsShowEndPage(false);
            paginationDto.setIsShowNext(false);
            paginationDto.setIsShowFirstPage(false);
            paginationDto.setIsShowPrevious(false);
            model.addAttribute("pagination",paginationDto);
            model.addAttribute("title","最新回复");
        }
        return "myPublish";

    }

}
