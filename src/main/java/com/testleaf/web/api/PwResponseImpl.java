package com.testleaf.web.api;

import com.microsoft.playwright.APIResponse;

import java.util.Map;

public class PwResponseImpl implements ResponseAPI{

    private APIResponse response;

    public PwResponseImpl(APIResponse response){
        this.response = response;
    }
    @Override
    public int getStatusCode() {
        return response.status();
    }

    @Override
    public String getStatusMessage() {
        return response.statusText();
    }

    @Override
    public String getResponseBody() {
        return new String(response.body());
    }

    @Override
    public Map<String, String> getResponseHeader() {
        return response.headers();
    }
}
