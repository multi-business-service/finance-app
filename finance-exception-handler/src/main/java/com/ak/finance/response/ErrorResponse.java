package com.ak.finance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String group;
    private String type;
    private String code;
    private String source;
    private Date timestamp;
    private String application;
    private String message;

    private String path;

    public ErrorResponse(String group, String type, String code, String source, Date timestamp, String application,
                         String message, String path){
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.timestamp = timestamp;
        this.application = application;
        this.message = message;
        this.path = path;
    }
}
