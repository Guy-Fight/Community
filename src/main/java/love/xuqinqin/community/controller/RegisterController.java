package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.PassUserDTO;
import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.Pass;
import love.xuqinqin.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author FGuy
 * @Date 2020/3/6 15:28
 */
@Controller
public class RegisterController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request){
        Pass pass = new Pass();
        pass.setUsername((String)request.getAttribute("username"));
        pass.setPassword((String)request.getAttribute("password"));
        User user = new User();
        user.setName((String)request.getAttribute("name"));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());

        userMapper.insertZc(user);

        return "/register";
    }

}
