package love.xuqinqin.community.mapper;

import love.xuqinqin.community.model.User;
import org.apache.ibatis.annotations.*;

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
    User SelectById(@Param("id") String id);

    @Select("select * from User where accountId = '${accountId}'")
    User SelectByAccountId(@Param("accountId") String accountId);

    @Update("update User set name = '${name}',token = '${token}',gmtCreate = ${gmtCreate},gmtModified = ${gmtModified},bio = '${bio}',avatar_url = '${avatar_url}' where accountId = '${accountId}'")
    void UpdateByToken(User user);

}
