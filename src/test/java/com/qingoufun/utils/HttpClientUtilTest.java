package com.qingoufun.utils;

import com.qingofun.utils.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qinkang on 16/7/1.
 */
public class HttpClientUtilTest {

    @Test
    public void testPost() {
        String url = "http://10.100.138.119:8080/investments/create.json";// 创建单笔投资
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("source", 101);
        params.put("terminal", 1);
        params.put("product_id", "2015081815555522463");
        params.put("app_order_id", "NP201511251421_TEST02");// 系统投资单号
        params.put("buy_amount", 10010.00);// 认购金额
        params.put("discount_fee", 100.00);// 折扣费
        params.put("app_uid", "100129654580");// 渠道用户编号（这个必须是真实存在的，目前方案待考虑）
        params.put("product_period", 6);// 产品期数(产品类型为还贷宝时必填，6期、12期)
        params.put("repay_day", 10);// 回款日(1-31，表示每月的那天回款)
        params.put("period_repay_amount", 100);// 每期回款金额
        params.put("first_repay_amount", 100);// 首期回款金额

        params.put("sign", "091fc28109ea4e0cb3c6877b09bc782f");

        String result = HttpClientUtil.post(url, params);
        // String result = RestClient.postValues(params, url, String.class);
        System.out.println(result);
    }
}
