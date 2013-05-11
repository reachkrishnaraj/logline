package com.vijayrc.supportguy.web;

import com.vijayrc.supportguy.controller.AllControllers;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public class HttpTask implements Runnable {
    private static final Logger log = Logger.getLogger(HttpTask.class);

    private Request request;
    private Response response;
    private AllControllers allControllers;

    public HttpTask(Request request, Response response, AllControllers allControllers) {
        this.request = request;
        this.response = response;
        this.allControllers = allControllers;
    }

    @Override
    public void run() {
        try {
            allControllers.act(request, response);
        } catch (Exception e) {
            log.error(request.getPath(), e);
        }
    }
}

