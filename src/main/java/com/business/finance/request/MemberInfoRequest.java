package com.business.finance.request;

import com.business.finance.constrants.AppEnumConstants;
import lombok.Data;

@Data
public class MemberInfoRequest {

    String firstName;
    String lastName;
    String aadaarNo;
    String mobileNo;
    String alternateNo;

    // as of now it is just a string, later can be updated as an object with all details.
    String address;

    Enum<AppEnumConstants.MemberRole> role;

    // As of now it is enabled only for admin user, it is optional for others
    String userName; // username is nothing but aadaar number;
    String password;
}
