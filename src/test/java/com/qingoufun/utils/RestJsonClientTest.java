package com.qingoufun.utils;

import com.qingofun.pojo.req.RequestJsonVO;
import com.qingofun.pojo.res.ResponseJsonVo;
import com.qingofun.utils.JsonUtil;
import com.qingofun.utils.RestJsonClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qinkang on 16/6/28.
 */
public class RestJsonClientTest {

    @Test
    public void testPostForEntity() {
        String url = "http://localhost:8081/product-web/user/createUser.htm";

        String enJsonData = "0E05088909FC6B566B61776D0CBCA8A0845B2AAC7E7EC9C3E5E690CA3D871B60653297B0089AF6EBDF2C5A09A4B2F63DC19000A0AA6573D4A24FCD1663C48C1574EE394AF943E5981F8591D4E732D2C2F429AB0AE84F1F52D8DE700353A6DF97562D3FB52A91A2D1A7688960EEE92EBDA6AD4F2A8F1A619BC9A17DBE5E9AE250D5145A0AEADE78C2FC56477E6CED08F33DE7042FFC454A6B313CE74C231CF3067B1927185123BFD89988BA8C939B3F6FF051B7B8968A7567120061301CA7E730F5FCAEAD5404E9C0CE9AB818C5DBAD37";
        String sign = "c552ec0b9698771addaa22d6a640d0bf";
        String merchantId = "abc";
        RequestJsonVO reqVo = new RequestJsonVO(sign, enJsonData, merchantId);

        String response = RestJsonClient.postForEntity(reqVo, url, String.class);
        System.out.println(response);

        ResponseJsonVo responseJsonVo = RestJsonClient.postForEntity(reqVo, url, ResponseJsonVo.class);
        System.out.println(JsonUtil.objectToJsonString(responseJsonVo));
    }

    @Test
    public void testPostForObject() {
        String url = "http://localhost:8081/product-web/user/createUser.htm";

        String enJsonData = "0E05088909FC6B566B61776D0CBCA8A0845B2AAC7E7EC9C3E5E690CA3D871B60653297B0089AF6EBDF2C5A09A4B2F63DC19000A0AA6573D4A24FCD1663C48C1574EE394AF943E5981F8591D4E732D2C2F429AB0AE84F1F52D8DE700353A6DF97562D3FB52A91A2D1A7688960EEE92EBDA6AD4F2A8F1A619BC9A17DBE5E9AE250D5145A0AEADE78C2FC56477E6CED08F33DE7042FFC454A6B313CE74C231CF3067B1927185123BFD89988BA8C939B3F6FF051B7B8968A7567120061301CA7E730F5FCAEAD5404E9C0CE9AB818C5DBAD37";
        String sign = "c552ec0b9698771addaa22d6a640d0bf";
        String merchantId = "abc";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sign", sign);
        map.put("data", enJsonData);
        map.put("merchant_id", merchantId);

        String response = RestJsonClient.postForObject(map, url, String.class);
        System.out.println(response);

    }

    @Test
    public void testGetForObject() {
        String url = "http://localhost:8080/product-task-web/init/systeminit.htm?" +
                "uid={uid}";

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("uid", "154b3a12a681309276712f9e1e283182");
        String response = RestJsonClient.getForObject(url, String.class, map1);
        System.out.println(response);

    }

    @Test
    public void testGetForEntity() {
        String url = "http://localhost:8080/product-task-web/init/systeminit.htm?" +
                "uid={uid}";

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("uid", "154b3a12a681309276712f9e1e283182");
        String response = RestJsonClient.getForEntity(url, String.class, map1);
        System.out.println(response);

    }

    @Test
    public void testGetForEntity1() {

        String url = "http://localhost:8080/product-task-web/init/systeminit.htm?" +
                "uid={uid}";
        String uid = "154b3a12a681309276712f9e1e283182";
        String response = RestJsonClient.getForEntity(uid, url, String.class);
        System.out.println(response);

    }

    @Test
    public void testGetForEntity2() {

        String url = "http://localhost:8080/product-admin-web/notice/fail/list" ;
        String response = RestJsonClient.getForObject(url, String.class);
        System.out.println(response);

    }


}
