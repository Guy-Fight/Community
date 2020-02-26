package love.xuqinqin.community.provider;

import com.alibaba.fastjson.JSON;
import love.xuqinqin.community.dto.AccessTokenDTO;
import love.xuqinqin.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * @Author FGuy
 * @Date 2020/2/26 17:12
 */
@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        //设置json格式
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //创建okHttp对象
        OkHttpClient client = new OkHttpClient();
        //创建请求体
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        //封装post请求
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            //接收响应
            Response response = client.newCall(request).execute();
            //获取响应内容
            String string = response.body().string();
            //提取token码
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken){
        //创建okHttp对象
        OkHttpClient client = new OkHttpClient();
        //封装get请求
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token=" + accessToken)
                    .build();
        try {
            //接收响应
            Response response = client.newCall(request).execute();
            //获取响应内容
            String string = response.body().string();
            //获取用户信息json，并转化为GithubUser对象接收；
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
