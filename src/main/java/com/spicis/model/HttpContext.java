package com.spicis.model;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class HttpContext {
    private int userId;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}
