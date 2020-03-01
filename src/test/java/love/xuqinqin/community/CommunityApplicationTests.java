package love.xuqinqin.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() throws IOException {
        int maxPage = (int)Math.ceil(20/8)+1;
        System.out.println(Math.ceil(20/8));
    }

}
