package com.wangkang.exception;



import static com.wangkang.util.ConstantUtil.MODEL_CODE;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:05 2018/12/26
 * @Modified By:
 */
public enum MessageCode implements ErrorCodeDefine {

    PAGESIZE_NOT_VALID(MODEL_CODE+"01","pagesize not valid",ErrorLevel.NOTICE.getCode()),
    ;

    private String code;
    private String msg;
    private String level;

    MessageCode(String code, String msg, String level) {
        this.code = code;
        this.msg = msg;
        this.level = level;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLevel() {
        return this.level;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
