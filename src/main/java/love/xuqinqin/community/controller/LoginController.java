package love.xuqinqin.community.controller;

import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author FGuy
 * @Date 2020/2/28 8:50
 */

@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String Login(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                request.getSession().setAttribute("user",userMapper.Select(token));
                break;
            }
        }



        return "index";
    }

}
