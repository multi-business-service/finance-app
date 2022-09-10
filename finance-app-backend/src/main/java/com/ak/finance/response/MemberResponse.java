package com.ak.finance.response;

import com.ak.finance.constrants.AppEnumConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class MemberResponse {

    String firstName;
    String lastName;
    String aadaarNo;
    String mobileNo;
    String alternateNo;

    // as of now it is just a string, later can be updated as an object with all details.
    String address;

    AppEnumConstants.MemberRole role;

    String cuid;

    // As of now it is enabled only for admin user, it is optional for others
    String userName; // username is nothing but aadaar number;
    String password;

    // tells whether member added/updated/deleted
    String operation;
    AppEnumConstants.OperationStatus OperationStatus;
    String infoMessage;
}
