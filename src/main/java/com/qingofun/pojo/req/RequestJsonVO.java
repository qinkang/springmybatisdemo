package com.qingofun.pojo.req;

import java.io.Serializable;

/**
 * Created by qinkang on 16/6/28.
 */
public class RequestJsonVO implements Serializable {
    private String sign;
    private String data;
    private String merchant_id;

    public RequestJsonVO() {
    }

    public RequestJsonVO(String sign, String data, String merchantId) {
        this.sign = sign;
        this.data = data;
        this.merchant_id = merchantId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }
}
