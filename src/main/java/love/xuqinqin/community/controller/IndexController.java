package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
    public String index(Model model){
        List<QuestionDto> questionDtos = questionService.getQuestionDto();
        model.addAttribute("questions",questionDtos);
        return "/index";
    }

}
