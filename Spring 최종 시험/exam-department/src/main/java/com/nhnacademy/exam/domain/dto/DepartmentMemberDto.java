package com.nhnacademy.exam.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentMemberDto {
    private DepartmentsDto department;
    private EmployeesDto employee;

    @QueryProjection
    public DepartmentMemberDto(DepartmentsDto department, EmployeesDto employee) {
        this.department = department;
        this.employee = employee;
    }
}