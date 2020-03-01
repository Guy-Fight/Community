package love.xuqinqin.community.mapper;

import love.xuqinqin.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author FGuy
 * @Date 2020/2/27 17:29
 */
@Mapper
public interface UserMapper {

    @Insert("insert into User (name,accountId,token,gmtCreate,gmtModified,bio,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatar_url})")
    void insert(User user);

    @Select("select * from User where token = '${token}'")
    User Select(@Param("token") String token);

    @Select("select * from User where id = '${id}'")
    User SelectByAccountId(@Param("id") String id);

}
