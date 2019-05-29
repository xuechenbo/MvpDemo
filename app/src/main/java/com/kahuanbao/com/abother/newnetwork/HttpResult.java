package com.kahuanbao.com.abother.newnetwork;



/**
 * Description: 网络返回消息，最外层解析实体
 */
public class HttpResult {
    /**
     * error : 500
     * msg : 服务器繁忙，请稍候再试
     * data :
     */

    private int code;
    private String msg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
