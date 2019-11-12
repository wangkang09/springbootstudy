package com.wangkang.exception;

/**
 * @Description: 总的项目的异常代码规定
 * @Author: wangkang
 * @Date: Created in 9:56 2018/12/26
 * @Modified By:
 */
public interface ErrorCodeDefine {
    String getMsg();
    String getCode();
    String getLevel();

    enum ErrorLevel {
        NORMAL("0"),
        INFO("1"),
        NOTICE("2"),
        WARN("3"),
        ERROR("4"),
        ;

        private String code;
        private ErrorLevel(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    enum MessageCode {
        OK("0", "OK", ErrorCodeDefine.ErrorLevel.NORMAL.getCode()),
        BADREQUEST("0001", "bad reqeust", ErrorCodeDefine.ErrorLevel.NOTICE.getCode()),
        UNKNOWERROR("0002", "internal server error", ErrorCodeDefine.ErrorLevel.WARN.getCode()),
        VALIDERROR("0003", "request params valid error", ErrorCodeDefine.ErrorLevel.NOTICE.getCode()),
        TOKENEMPTY("0004", "token empty", ErrorCodeDefine.ErrorLevel.NOTICE.getCode()),
        TOKENEERR("0005", "token error", ErrorCodeDefine.ErrorLevel.NOTICE.getCode()),
        DOWNSTREAMERROR("0006", "down stream error", ErrorCodeDefine.ErrorLevel.NOTICE.getCode());

        private String code;
        private String msg;
        private String level;

        private MessageCode(String code, String msg, String level) {
            this.code = code;
            this.msg = msg;
            this.level = level;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code, String msg) {
            this.code = code;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getLevel() {
            return this.level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static ErrorCodeDefine.MessageCode getMessageCode(String code) {
            ErrorCodeDefine.MessageCode[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ErrorCodeDefine.MessageCode it = var1[var3];
                if(it.getCode() == code) {
                    return it;
                }
            }

            return UNKNOWERROR;
        }
    }
}
