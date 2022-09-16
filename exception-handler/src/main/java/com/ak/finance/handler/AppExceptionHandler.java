package com.ak.finance.handler;

import com.ak.finance.constrants.AppConstants;
import com.ak.finance.constrants.AppProperties;
import com.ak.finance.constrants.ExceptionEnumConstants;
import com.ak.finance.exception.FinanceRunTimeException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class AppExceptionHandler {
    @Autowired
    private AppProperties appProperties;
    public FinanceRunTimeException handleDBException(Exception ex){
        if (ex.getCause() instanceof ConstraintViolationException){
            return  new FinanceRunTimeException(
                    ExceptionEnumConstants.Group.DATA_ERROR, ExceptionEnumConstants.Type.DATABASE_ERROR, ExceptionEnumConstants.Code.DATA_ALREADY_EXIST, ExceptionEnumConstants.Source.INTERNAL, ExceptionEnumConstants.Severity.LOW,
                    appProperties.getAppName(), "Data already exist", Timestamp.valueOf(LocalDateTime.now(ZoneId.of(appProperties.getZoneId()))),
                    ex,  HttpStatus.BAD_REQUEST);
        }
        return new FinanceRunTimeException(
                ExceptionEnumConstants.Group.SYSTEM_INTERNAL_ERROR, ExceptionEnumConstants.Type.INTERNAL_ERROR, ExceptionEnumConstants.Code.INTERNAL_ERROR, ExceptionEnumConstants.Source.INTERNAL, ExceptionEnumConstants.Severity.HIGH,
                appProperties.getAppName(), "System failed: "+ex.getMessage(), Timestamp.valueOf(LocalDateTime.now(ZoneId.of(appProperties.getZoneId()))),
                ex,  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
