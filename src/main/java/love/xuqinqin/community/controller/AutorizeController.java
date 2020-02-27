package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.AccessTokenDTO;
import love.xuqinqin.community.dto.GithubUser;
import love.xuqinqin.community.model.User;
import love.xuqinqin.community.provider.GitHubProvider;
import love.xuqinqin.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author FGuy
 * @Date 2020/2/26 17:03
 */
@Controller
public class AutorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirect;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpSession session) throws Exception {
        //封装tokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect);
        accessTokenDTO.setState(state);
        //根据AccessTokenDTO对象转化的json作为请求体post请求，响应得到token码；
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        //根据token码作为请求体get请求，响应得到用户json，并转化为GithubUser对象接收；
        GithubUser user = gitHubProvider.getUser(accessToken);
        if(user != null){
            //登录成功
            //session
            session.setAttribute("user",user);
            userService.Ins(new User(2,user.getName(),String.valueOf(user.getId()),accessToken,"demo","demo"));
            //cookie



        }else{
            //登录失败

        }
        //返回index页面
        return "redirect:/";
    }

}
