package com.ak.exception.exception;

import com.ak.exception.constrants.ExceptionEnumConstants;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class FinanceRunTimeException extends RuntimeException {

    private final ExceptionEnumConstants.Group group;
    private final ExceptionEnumConstants.Type type;
    private final ExceptionEnumConstants.Code code;
    private final ExceptionEnumConstants.Source source;
    private final ExceptionEnumConstants.Severity severity;
    private Date timestamp;
    private String system;
    private String message;
    private Throwable originalException;
    private HttpStatus httpStatus;

    public FinanceRunTimeException(ExceptionEnumConstants.Group group, ExceptionEnumConstants.Type type, ExceptionEnumConstants.Code code, ExceptionEnumConstants.Source source, ExceptionEnumConstants.Severity severity,
                                   String system, String message, Date timestamp, Throwable ex, HttpStatus httpStatus) {
        super(message, ex);
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.severity = severity;
        this.timestamp = timestamp;
        this.system = system;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }


    public FinanceRunTimeException(ExceptionEnumConstants.Group group, ExceptionEnumConstants.Type type, ExceptionEnumConstants.Code code, ExceptionEnumConstants.Source source, ExceptionEnumConstants.Severity severity,
                                   String message, Date timestamp, Throwable ex, HttpStatus httpStatus) {
        super(message, ex);
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.severity = severity;
        this.timestamp = timestamp;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }

    public FinanceRunTimeException(ExceptionEnumConstants.Group group, ExceptionEnumConstants.Type type, ExceptionEnumConstants.Code code, ExceptionEnumConstants.Source source, ExceptionEnumConstants.Severity severity,
                                   String application, String message, Throwable ex, HttpStatus httpStatus) {
        super(message, ex);
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.severity = severity;
        this.system = application;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }

}
