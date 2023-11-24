package com.example.confrenceroombooking.model;

public class RequestPayload<T> {
    private Header header;
    private T requestUser;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(T requestUser) {
        this.requestUser = requestUser;
    }
}
