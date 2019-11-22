package com.shente.cams.util;
/*
 *@author sugar
 *2019/10/28
 *9:57
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    /**
     * 加密秘钥
     */
    private final static String KEY="swpuKerno";

    private static Map<String,Object> map=new HashMap<>();
    private static ObjectMapper objectMapper=new ObjectMapper();

    private static JsonNode userNode=null;

    /**
     * 生成一个新的令牌
     * @param uId 用户主键
     * @param account 账号
     * @param overtime 过期时间(分,0永远不过期)
     * @return
     */
    public static String create(Integer uId,String account,Integer overtime) throws Exception {
        map.clear();
        map.put("uId",uId);
        map.put("account",account);
        map.put("overtime",overtime);

        //创建时间
        map.put("createDate",System.currentTimeMillis());
        return DesUtil.encrypt(objectMapper.writeValueAsString(map),KEY);
    }

    /**
     * 解密token字符串
     * @param tokenStr 令牌字符串
     * @return 解密后的JSON字符串内容
     */
    private static String decode(String tokenStr) throws Exception {
        return DesUtil.decrypt(tokenStr,KEY);
    }

    /**
     * 判断令牌是否有效
     * @return Boolean
     */
    public static Boolean isValid(String tokenStr){
        try{
            tokenStr=tokenStr.replace(" ","");
            String orignJsonStr = decode(tokenStr);
            userNode = objectMapper.readTree(orignJsonStr);
            JsonNode overtime = userNode.get("overtime");
            JsonNode createDate = userNode.get("createDate");
            //过期时间
            long endDate=createDate.asLong()+overtime.asLong()*60*1000;
            return endDate > System.currentTimeMillis();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取用户id
     * @return 用户id
     */
    public static Integer getUserId(){
        return userNode.get("uId").asInt();
    }

    /**
     * 获取账号
     * @return 用户账号
     */
    public static String getAccount(){
        return userNode.get("account").asText();
    }
}
