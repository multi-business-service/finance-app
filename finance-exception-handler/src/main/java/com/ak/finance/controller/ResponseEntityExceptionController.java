package com.ak.finance.controller;

import com.ak.finance.constrants.ExceptionEnumConstants.Code;
import com.ak.finance.constrants.ExceptionEnumConstants.Group;
import com.ak.finance.constrants.ExceptionEnumConstants.Severity;
import com.ak.finance.constrants.ExceptionEnumConstants.Source;
import com.ak.finance.constrants.ExceptionEnumConstants.Type;
import com.ak.finance.exception.FinanceRunTimeException;
import com.ak.finance.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;


@ControllerAdvice
public class ResponseEntityExceptionController extends ResponseEntityExceptionHandler {

    private final ZoneId DEFAULT_ZONE_ID = ZoneId.of("UTC");
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(Group.APPLICATION_ERROR,
                Type.APP_CONFIG_ERROR, Code.ILLEGAL_ARGUMENT_FOUND, Source.INTERNAL, Severity.LOW, "Illegal Argument Exception occurred",
                Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({SAXException.class, JAXBException.class})
    protected ResponseEntity<Object> handleSaxExceptions(Exception ex, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(Group.VALIDATION_ERROR, Type.JACKSON_ERROR,
                Code.PARSING_ERROR, Source.INTERNAL, Severity.LOW,  ex.getMessage(),
                Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), ex, HttpStatus.BAD_REQUEST);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(Group.VALIDATION_ERROR, Type.JACKSON_ERROR,
                Code.PARSING_ERROR, Source.INTERNAL, Severity.LOW,  this.constructErrorMessageForSchemaErrors(ex),
                Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), ex, HttpStatus.BAD_REQUEST);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(Group.VALIDATION_ERROR, Type.HTTP_MESSAGE_NOT_READBLE_ERROR,
                Code.SCHEMA_VALIDATON_ERROR, Source.INTERNAL, Severity.LOW,  ex.getMessage(),
                Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), ex, HttpStatus.BAD_REQUEST);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({FinanceRunTimeException.class})
    protected ResponseEntity<Object> handleFinanceRunTimeException(FinanceRunTimeException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, ex, this.getResponseHeaders(request), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(Group.SYSTEM_INTERNAL_ERROR,
                Type.INTERNAL_ERROR, Code.INTERNAL_ERROR, Source.INTERNAL,
                Severity.HIGH, "Null pointer exception", Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)),
                ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

//    @ExceptionHandler(SQLException.class)
//    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
//        FinanceRunTimeException exception = new FinanceRunTimeException(Group.VALIDATION_ERROR, Type.CART_VALIDATION_ERROR.name(), Code.SCHEMA_VALIDATON_ERROR.name(), "SPE", "Message not valid", Severity.LOW, Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), HttpStatus.BAD_REQUEST, new Info(this.constructErrorMessageForSchemaErrors(ex)));
//        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.BAD_REQUEST, request);
//    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleUnknownException(RuntimeException ex, WebRequest request) {
        FinanceRunTimeException exception = new FinanceRunTimeException(
                Group.SYSTEM_INTERNAL_ERROR, Type.INTERNAL_ERROR, Code.INTERNAL_ERROR, Source.INTERNAL, Severity.HIGH,
                "AK-FINANCE", "Runtime Exception", Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)),
                ex,  HttpStatus.INTERNAL_SERVER_ERROR);
        return this.handleExceptionInternal(ex, exception, this.getResponseHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private HttpHeaders getResponseHeaders(WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("application-name", request.getHeader("X-Application-Id"));
        httpHeaders.add("X-Flow-ID", request.getHeader("X-Flow-ID"));
        httpHeaders.add("Content-Type", request.getHeader("Content-Type"));
        return httpHeaders;
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = body instanceof FinanceRunTimeException ?
                new ErrorResponse(
                ((FinanceRunTimeException)body).getGroup().name(),
                ((FinanceRunTimeException)body).getType().name(),
                ((FinanceRunTimeException)body).getCode().name(),
                ((FinanceRunTimeException)body).getSource().name(),
                ((FinanceRunTimeException)body).getTimestamp(),
                ((FinanceRunTimeException)body).getApplication(),
                ((FinanceRunTimeException)body).getMessage(),
                ((ServletWebRequest)request).getRequest().getRequestURI())
                : new ErrorResponse(
                Group.SYSTEM_INTERNAL_ERROR.name(),
                Type.INTERNAL_ERROR.name(),
                Type.INTERNAL_ERROR.name(),
                Source.INTERNAL.name(),
                Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)),
                null,
                ex.getMessage(),
                ((ServletWebRequest)request).getRequest().getRequestURI());
//        if (ApplicationConstants.errorLogger.isErrorEnabled()) {
//            ServiceContext serviceContext = this.auditUtil.createServiceContext();
//
//            try {
//                AuditInfo governanceInfo = this.auditUtil.getAuditInfoFromMDC(MDC.get("SPE"), DirectionEnum.FAIL);
//                FinanceRunTimeException exception;
//                if (ex instanceof FinanceRunTimeException) {
//                    exception = (FinanceRunTimeException)ex;
//                    governanceInfo.setInboundApplicationId(((FinanceRunTimeException)ex).getSystem());
//                    String customRequestUri = ((FinanceRunTimeException)ex).getCustomRequestUri();
//                    if (!Strings.isNullOrEmpty(customRequestUri)) {
//                        governanceInfo.setRequestUri(customRequestUri);
//                    }
//                } else if (body instanceof FinanceRunTimeException) {
//                    exception = (FinanceRunTimeException)body;
//                } else {
//                    exception = new FinanceRunTimeException(errorInfo.getGroup(), errorInfo.getType(), errorInfo.getCode(), errorInfo.getSystem(), errorInfo.getMessage(), Severity.MEDIUM, Timestamp.valueOf(LocalDateTime.now(this.DEFAULT_ZONE_ID)), HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//
//                ApplicationConstants.errorLogger.error(Markers.appendRaw("audit", this.objectMapper.writeValueAsString(governanceInfo)).and(Markers.appendRaw("error", exception.getErrorLogMessage())), (String)null, StructuredArguments.kv("message", ExceptionUtils.getStackTrace(ex)), StructuredArguments.raw("serviceContext", serviceContext.toString()));
//            } catch (JsonProcessingException var11) {
//                ApplicationConstants.errorLogger.error(Markers.appendRaw("error", Type.JACKSON_ERROR.name()), (String)null, StructuredArguments.kv("message", StructuredArguments.kv("message", ExceptionUtils.getStackTrace(var11))), StructuredArguments.raw("serviceContext", serviceContext.toString()));
//            }
//        }

        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    private String constructErrorMessageForSchemaErrors(MethodArgumentNotValidException ex) {
        if (Objects.isNull(ex.getBindingResult())
                || Objects.isNull(ex.getBindingResult().getFieldError())
                || Objects.isNull(ex.getBindingResult().getFieldError().getObjectName())
                || Objects.isNull(ex.getBindingResult().getFieldError().getField())
                || Objects.isNull(ex.getBindingResult().getFieldError().getDefaultMessage()))
            return ex.getMessage();
        String fieldError = "Field error in object '%s' on field '%s', violated constraint : %s";
        FieldError exceptionValue = ex.getBindingResult().getFieldError();
        return String.format(fieldError, exceptionValue.getObjectName(), exceptionValue.getField(), exceptionValue.getDefaultMessage());
    }

//    private String constructErrorMessageForSchemaErrors(ConstraintViolationException ex) {
//        if (!CollectionUtils.isEmpty(ex.getConstraintViolations())) {
//            String fieldError = "Field error in object '%s' on field '%s', violated constraint : %s";
//            ConstraintViolation<?> exceptionValue = (ConstraintViolation)ex.getConstraintViolations().stream().findFirst().get();
//            return String.format(fieldError, exceptionValue.getPropertyPath(), exceptionValue.getInvalidValue(), exceptionValue.getMessage());
//        } else {
//            return ex.getMessage();
//        }
//    }
}
