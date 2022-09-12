package com.ak.finance.response;

import com.ak.finance.constrants.ExceptionEnumConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private ExceptionEnumConstants.Group group;
    private ExceptionEnumConstants.Source source;
    private ExceptionEnumConstants.Severity severity;
    private ExceptionEnumConstants.InternalCode code;
    private Date timestamp;
    private String application;
    private String Message;
    private String resourcePath;
}
