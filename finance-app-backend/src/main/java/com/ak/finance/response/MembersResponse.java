package com.ak.finance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)
public class MembersResponse {
    private List<MemberResponse> membersResponse;
}
