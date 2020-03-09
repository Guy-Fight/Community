package love.xuqinqin.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author FGuy
 * @Date 2020/2/28 8:50
 */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String Login(){
        return "/login";
    }

    @PostMapping("/login")
    public String postLogin(){

        return "/login";
    }

}
