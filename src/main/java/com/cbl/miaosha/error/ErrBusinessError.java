package com.cbl.miaosha.error;

public enum ErrBusinessError implements CommonError {

    PARAMETER_VALIDATION_ERROR(00001,"参数不合法"),
    UKNOWNERROR(00002,"未知错误"),
    USER_NOT_EXIST(10001,"用户不存在"),
    USERLOGIN_NOT_EXIST(10002,"用户名或密码错误")

;
    private int errCode;
    private String errMsg;

    private ErrBusinessError(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMSg(String errMSg) {
        this.errMsg=errMSg;
        return this;
    }
}
