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

    private final Group group;
    private final Type type;
    private final Code code;
    private final Source source;
    private final Severity severity;
    private Date timestamp;
    private String system;
    private String message;
    private Throwable originalException;
    private HttpStatus httpStatus;

    public FinanceRunTimeException(Group group, Type type, Code code, Source source, Severity severity,
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
        this.system = application;
        this.message = message;
        this.originalException = ex;
        this.httpStatus = httpStatus;
    }

}
