package com.nhnacademy.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeInfo {
    @JsonProperty("사번")
    String Id;
    @JsonProperty("이름")
    String name;
    @JsonProperty("부서")
    String departmentName;
    @JsonProperty("부서코드")
    String departmentId;
}
