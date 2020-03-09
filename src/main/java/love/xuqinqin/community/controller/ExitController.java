package love.xuqinqin.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author FGuy
 * @Date 2020/3/6 12:14
 */
@Controller
public class ExitController {

    @GetMapping("/exit")
    public String exit(HttpServletRequest request,
                       HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
