package com.ak.finance.response;

import com.ak.finance.constrants.AppEnumConstants;
import lombok.Data;

@Data
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
}
