package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.dto.DepartmentMemberDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DepartmentMemberCustom {
    List<DepartmentMemberDto> departmentList(String departmentId);
}
