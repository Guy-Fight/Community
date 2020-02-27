package love.xuqinqin.community.mapper;

import love.xuqinqin.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author FGuy
 * @Date 2020/2/27 17:29
 */
@Mapper
public interface UserMapper {

    @Insert("insert into User (name,accountId,token,gmtCreate,gmtModified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

}
