package com.ak.finance.exception;

import com.ak.finance.constrants.ExceptionEnumConstants.Code;
import com.ak.finance.constrants.ExceptionEnumConstants.Group;
import com.ak.finance.constrants.ExceptionEnumConstants.Severity;
import com.ak.finance.constrants.ExceptionEnumConstants.Source;
import com.ak.finance.constrants.ExceptionEnumConstants.Type;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class FinanceRunTimeException extends RuntimeException {

    private Group group;
    private Type type;
    private Code code;
    private Source source;
    private Severity severity;
    private Date timestamp;
    private String application;
    private String message;
    private Throwable originalException;
    private HttpStatus httpStatus;

    public FinanceRunTimeException(Group group, Type type, Code code, Source source, Severity severity,
                                   String application, String message, Date timestamp, Throwable ex, HttpStatus httpStatus) {
        super(message, ex);
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.severity = severity;
        this.timestamp = timestamp;
        this.application = application;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }


    public FinanceRunTimeException(Group group, Type type, Code code, Source source, Severity severity,
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

    public FinanceRunTimeException(Group group, Type type, Code code, Source source, Severity severity,
                                   String application, String message, Throwable ex, HttpStatus httpStatus) {
        super(message, ex);
        this.group = group;
        this.type = type;
        this.code = code;
        this.source = source;
        this.severity = severity;
        this.application = application;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }

}
