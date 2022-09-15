package com.ak.finance.request;

import com.ak.finance.controller.constrants.AppEnumConstants;
import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class MemberInfoRequest {

    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    BigInteger aadaarNo;
    @NotNull
    BigInteger mobileNo;
    BigInteger alternateNo;

    // as of now it is just a string, later can be updated as an object with all details.
    @NotNull
    String address;
    @NotNull
    AppEnumConstants.MemberRole role;

    // As of now it is enabled only for admin user, it is optional for others
    String userName; // username is nothing but aadaar number;
    String password;
}
