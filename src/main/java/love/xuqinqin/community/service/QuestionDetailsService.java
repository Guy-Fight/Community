package love.xuqinqin.community.service;

import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.mapper.PublishMapper;
import love.xuqinqin.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author FGuy
 * @Date 2020/3/4 9:44
 */
@Service
public class QuestionDetailsService {

    @Autowired
    PublishMapper publishMapper;

    @Autowired
    UserMapper userMapper;

    public boolean isNull(int Id){
        if(publishMapper.selectQuestion(Id) == null){
            return true;
        }else{
            return false;
        }
    }

    public QuestionDto getQuestionDto(int Id){
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(publishMapper.selectQuestion(Id),questionDto);
        questionDto.setUser(userMapper.SelectByAccountId(String.valueOf(questionDto.getCreator())));
        return questionDto;
    }

}
