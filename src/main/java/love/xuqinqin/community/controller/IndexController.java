package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.PaginationDto;
import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author FGuy
 * @Date 2020/3/1 11:24
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"/index","/"})
    public String index(@RequestParam(name="page",required = false)String page, Model model){
        int IntPage = 1;
        try{
            IntPage = Integer.valueOf(page);
        }catch (Exception e){
//            e.printStackTrace();
        }
        List<QuestionDto> questionDtos = questionService.getQuestionDto(IntPage);
        PaginationDto paginationDto = questionService.getPaginationDto();
        model.addAttribute("questions",questionDtos);
        model.addAttribute("pagination",paginationDto);
//        System.out.println(IntPage);
        return "/index";
    }

}
