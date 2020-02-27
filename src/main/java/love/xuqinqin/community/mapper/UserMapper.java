package love.xuqinqin.community.mapper;

import love.xuqinqin.community.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Author FGuy
 * @Date 2020/2/27 17:29
 */
@Repository
public interface UserMapper {

    void Ins(int id,String name,String accountId,String token,String gmtCreate,String gmtModified);
}
