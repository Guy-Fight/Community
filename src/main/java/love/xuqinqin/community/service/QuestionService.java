package love.xuqinqin.community.service;

import love.xuqinqin.community.dto.QuestionDto;
import love.xuqinqin.community.mapper.PublishMapper;
import love.xuqinqin.community.mapper.UserMapper;
import love.xuqinqin.community.model.Publish;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author FGuy
 * @Date 2020/3/1 11:38
 */
@Service
public class QuestionService {

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> getQuestionDto(){
        List<Publish> publishes = publishMapper.select();
        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        if(publishes == null || publishes.size() == 0){
            return null;
        }
        for (Publish publish : publishes) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(publish,questionDto);
//            questionDto.setGmt_create(new Date().getTime() - publish.getGmt_create());
            questionDto.setUser(userMapper.SelectByAccountId(String.valueOf(publish.getCreator())));
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }
}
