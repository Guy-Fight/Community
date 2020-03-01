package love.xuqinqin.community.controller;

import love.xuqinqin.community.dto.AccessTokenDTO;
import love.xuqinqin.community.dto.GithubUser;
import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.User;
import love.xuqinqin.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author FGuy
 * @Date 2020/2/26 17:03
 */
@Controller
public class AutorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirect;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
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
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if(githubUser != null){
            //登录成功
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUser.getBio());
            user.setAvatar_url(githubUser.getAvatar_url());
            userMapper.insert(user);
            //session
            request.getSession().setAttribute("user",githubUser);
            //cookie
            response.addCookie(new Cookie("token",user.getToken()));
        }else{
            //登录失败
            //request.getSession().setAttribute("login_tip","登陆失败，请重试");
        }
        //返回index页面
        return "redirect:/";
    }

}
