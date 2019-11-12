package com.wangkang.exception;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 9:52 2018/12/26
 * @Modified By:
 */
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;
    private String level;
    private ErrorCodeDefine errorCodeDefine;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ErrorCodeDefine errorCodeDefine) {
        super(errorCodeDefine.getMsg());
        this.errorCodeDefine = errorCodeDefine;
        this.code = errorCodeDefine.getCode();
        this.msg = errorCodeDefine.getMsg();
        this.level = errorCodeDefine.getLevel();
    }

    public BusinessException(String msg, ErrorCodeDefine errorCodeDefine) {
        super(msg);
        this.msg = msg;
        this.errorCodeDefine = errorCodeDefine;
        this.code = errorCodeDefine.getCode();
        this.level = errorCodeDefine.getLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessException that = (BusinessException) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        return errorCodeDefine != null ? errorCodeDefine.equals(that.errorCodeDefine) : that.errorCodeDefine == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (errorCodeDefine != null ? errorCodeDefine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", level='" + level + '\'' +
                ", errorCodeDefine=" + errorCodeDefine +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ErrorCodeDefine getErrorCodeDefine() {
        return errorCodeDefine;
    }

    public void setErrorCodeDefine(ErrorCodeDefine errorCodeDefine) {
        this.errorCodeDefine = errorCodeDefine;
    }
}
