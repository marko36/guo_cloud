package com.jc.cloud.common.msg;

/**
 * @ClassName ObjectRestResponse
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class ObjectRestResponse<T> extends BaseResponse {

    T data;
    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ObjectRestResponse ok() {
       return this.ok("ok");
    }
    public ObjectRestResponse ok(Object data) {
        this.setSuccess(true);
        this.data= (T) data;
        return this;
    }

    public ObjectRestResponse error() {
        return this.error("error");
    }

    public ObjectRestResponse error(String message) {
        this.setSuccess(false);
        this.setMessage(message);
        return this;
    }

    public ObjectRestResponse rel(boolean rel) {
        this.setSuccess(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        this.setSuccess(true);
        return this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
