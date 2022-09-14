package com.ak.finance.constrants;

import lombok.Data;

@Data
public class ExceptionEnumConstants {
    public static enum JwtErrorCode {
        TOKEN_EXPIRED,
        INVALID_ISSUER,
        INVALID_TOKEN,
        UNAUTHORIZED_CLIENT;
    }

    public static enum DatabaseErrorCode {
        DB_ACCESS_ERROR,
        DB_SQL_ERROR,
        DB_CONNECTION_TIME_OUT,
        DB_QUERY_EXECUTION_FAILED;
    }

    public static enum ConnectionFailureCode {
        CONNECTION_TIMEOUT,
        READ_TIMEOUT,
        UNKNOWN_HOST,
        HOST_CONNECTION_FAILURE,
        IO_CONNECTION_FAILURE;
    }

    public static enum Source {
        INTERNAL,
        EXTERNAL;
    }

    public static enum Severity {
        LOW,
        MEDIUM,
        HIGH;
    }

    public static enum Type {
        EXTERNAL_CONNECTION_ERROR,
        INTERNAL_CONNECTION_ERROR,
        INTERNAL_CLIENT_ERROR,
        INTERNAL_SERVER_ERROR,
        EXTERNAL_CLIENT_ERROR,
        EXTERNAL_SERVER_ERROR,
        APP_VALIDATION_ERROR,
        HTTP_MESSAGE_NOT_READBLE_ERROR,
        DATA_CONFIG_ERROR,
        APP_CONFIG_ERROR,
        SCHEMA_ERROR,
        DATABASE_ERROR,
        INTERNAL_ERROR,
        UNAUTHORIZED_ACCESS,
        JACKSON_ERROR,
        PROTO_ERROR;
    }

    public static enum Code {
        INTERNAL_ERROR,
        PARSING_ERROR,
        SCHEMA_VALIDATON_ERROR,
        ILLEGAL_ARGUMENT_FOUND,
        DATA_NOT_FOUND,
        DATA_ALREADY_EXIST,
        MESSAGE_NOT_READABLE;
    }

    public static enum Group {
        CONNECTIVITY_ERROR,
        CONFIGURATION_ERROR,
        APPLICATION_ERROR,
        DATA_ERROR,
        VALIDATION_ERROR,
        AUTHENTICATION_ERROR,
        SYSTEM_INTERNAL_ERROR;
    }
}
