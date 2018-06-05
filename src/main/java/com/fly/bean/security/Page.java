package com.fly.bean.security;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class Page<T> {
    private String msg;
    private  Integer code;
    private Object data;
    private Long count;

    @Override
    public String toString() {
        return "Page{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", count=" + count +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Page(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Page() {
    }

    public Page(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Page (PageInfo pageInfo, Integer code){
        this.data=pageInfo.getList();
        this.count = pageInfo.getTotal();
        this.code = code;
    }
}
