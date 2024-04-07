package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentMemberRepository extends JpaRepository<DepartmentEmployee, String>, DepartmentMemberCustom {
}
