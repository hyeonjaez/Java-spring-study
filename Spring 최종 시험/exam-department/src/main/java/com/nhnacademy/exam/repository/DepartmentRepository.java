package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.dto.DepartmentDto;
import com.nhnacademy.exam.entity.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    Optional<DepartmentDto> findDepartmentById(String code);
}
