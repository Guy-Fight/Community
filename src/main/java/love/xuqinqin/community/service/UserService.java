package love.xuqinqin.community.service;

import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author FGuy
 * @Date 2020/2/27 17:28
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void Ins(User user){
         userMapper.Ins(user.getId(),user.getName(),user.getAccountId(),user.getToken(),user.getGmtCreate(),user.getGmtModified());
    }

}
