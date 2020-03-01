package love.xuqinqin.community.controller;

import love.xuqinqin.community.mapper.PublishMapper;
import love.xuqinqin.community.model.Publish;
import love.xuqinqin.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author FGuy
 * @Date 2020/2/28 18:23
 */
@Controller
public class PublishController {

    @Autowired
    private PublishMapper publishMapper;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
        //判断是否登录
        if(request.getSession().getAttribute("user") == null){
            return "redirect:/";
        }
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title")String title,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "tag")String tag,
                            HttpServletRequest request,
                            Map map){
        //判断是否登录
        if(request.getSession().getAttribute("user") == null){
            return "redirect:/";
        }

        map.put("title",title);
        map.put("description",description);
        map.put("tag",tag);
        if(title == null || title.equals("")){
            map.put("writeTip","标题不能为空");
            return "/publish";
        }
        if(description == null || description.equals("")){
            map.put("writeTip","内容不能为空");
            return "/publish";
        }
        if(tag == null || tag.equals("")){
            map.put("writeTip","标签不能为空");
            return "/publish";
        }

        //插入数据库操作
        Publish publish = new Publish();
        publish.setTitle(title);
        publish.setDescription(description);
        publish.setTag(tag);
        User user = (User)request.getSession().getAttribute("user");
        publish.setCreator(user.getId());
        publish.setGmt_create(System.currentTimeMillis());
        publish.setGmt_modified(publish.getGmt_create());
        publishMapper.insert(publish);


        //若格式无误则删除model的 title、description、tag参数
        map.remove("title");
        map.remove("description");
        map.remove("tag");
        map.put("writeTip","已发起");

        return "/publish";
    }

}
