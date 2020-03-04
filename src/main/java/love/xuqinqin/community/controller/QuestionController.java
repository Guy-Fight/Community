package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.service.QuestionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author FGuy
 * @Date 2020/3/4 9:29
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionDetailsService questionDetailsService;

    @GetMapping("/question/{questionId}")
    public String questionDetails(@PathVariable(name = "questionId")String questionIdO, Model model){

        int questionId = 1;
        try{
            questionId = Integer.valueOf(questionIdO);
        }catch (Exception e){
            //跳转错误页面
            System.out.println("跳转错误页面(不是数字):" + questionIdO);
        }

        if(questionDetailsService.isNull(questionId)){
            //跳转错误页面
            System.out.println("跳转错误页面(null):" + questionIdO);
        }else{
            QuestionDto questionDto = questionDetailsService.getQuestionDto(questionId);
            model.addAttribute("questionDto",questionDto);
        }
        return "/question";
    }

}
