package com.business.finance.constrants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class AppEnumConstants {
    public enum BusinessType{
        LOAN,
        VADDI,
        YOUTUBE;
    }

    public enum RePayMode{
        DAY,
        WEEK,
        MONTH,
        YEAR;
    }

    @AllArgsConstructor
    public enum MemberRole {
        ADMIN("ADMIN"),
        MEMBER("MEMBER");
        @Getter private String value;
        }
}
