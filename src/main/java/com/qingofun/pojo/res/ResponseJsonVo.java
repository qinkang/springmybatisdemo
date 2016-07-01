package com.qingofun.pojo.res;

/**
 * Created by qinkang on 16/6/28.
 */
public class ResponseJsonVo {
    private String msgCode;
    private String msgDes;

    private String sign;
    private String data;
    private String merchant_id;

    public ResponseJsonVo(){
    }
    public ResponseJsonVo(String msgCode,String msgDes){
        this.msgCode = msgCode;
        this.msgDes = msgDes;
    }
    public ResponseJsonVo(String msgCode,String msgDes,String sign,String data,String merchantId){
        this.msgCode = msgCode;
        this.msgDes = msgDes;
        this.sign = sign;
        this.data = data;
        this.merchant_id = merchantId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
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
