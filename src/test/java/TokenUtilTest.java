
/*
 *@author sugar
 *2019/10/28
 *10:10
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shente.cams.util.DesUtil;
import com.shente.cams.util.TokenUtils;
import org.junit.Test;

import java.util.Date;

public class TokenUtilTest {

    @Test
    public void testCreate() throws Exception {
        String tokenStr=TokenUtils.create(4,"admin",60);
        System.out.println(tokenStr);
    }

    @Test
    public void testDecode() throws Exception {
        System.out.println(DesUtil.decrypt("50uiC11FaVfRfsqhiYK5LyljkFJE1854eZRrTqdChpQN571OJ6pNhFBvOpjjNTmP","swpuKerno"));
    }

    @Test
    public void testFormatContent()throws Exception{
        if (TokenUtils.isValid("50uiC11FaVfRfsqhiYK5LyljkFJE1854eZRrTqdChpQN571OJ6pNhAGPJHKHWUEnnDxNzWRr0bI4\n" +
                "dMirEPbLuXN8eE7M64Yp")){
            System.out.println(TokenUtils.getUserId());
        }
    }
}
