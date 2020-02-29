package love.xuqinqin.community.component;

import love.xuqinqin.community.mapper.UserMapper;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author FGuy
 * @Date 2020/2/28 15:33
 */
public class CookieInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            /*
            * 遍历Cookie，若Cookie中有token，且token值在数据库中存在，则存入session，记为已登录。
            *            若没有token值，或token值在数据库中不存在，则判断session中user值不为空后，删除user值。记为未登录，或退出登录。(最好把token值也删除)
            * */
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    if(userMapper.Select(token) != null){
                        session.setAttribute("user",userMapper.Select(token));
                        return true;
                    }
                }
            }
            if(session.getAttribute("user") != null){
                session.removeAttribute("user");
            }
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    //设置时长为0，视为删除
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
