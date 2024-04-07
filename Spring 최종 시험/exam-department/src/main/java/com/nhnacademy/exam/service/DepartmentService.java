package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.dto.DepartmentDto;
import com.nhnacademy.exam.domain.dto.DepartmentMemberDto;
import com.nhnacademy.exam.domain.request.DepartmentModifyRequest;
import com.nhnacademy.exam.domain.request.DepartmentRegisterRequest;
import com.nhnacademy.exam.domain.response.DepartmentRegisterResponse;
import java.util.List;

public interface DepartmentService {
    DepartmentRegisterResponse create(DepartmentRegisterRequest departmentRegisterRequest);

    DepartmentDto getDepartment(String departmentId);

    boolean modifyDepartment(String departmentId, DepartmentModifyRequest departmentModifyRequest);

    boolean deleteDepartment(String departmentId);

    List<DepartmentMemberDto> getAllDepartmentMember(String departmentId);
}
