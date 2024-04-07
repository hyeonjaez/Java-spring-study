package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.dto.DepartmentMemberDto;
import com.nhnacademy.exam.domain.dto.DepartmentsDto;
import com.nhnacademy.exam.domain.dto.EmployeesDto;
import com.nhnacademy.exam.entity.QDepartment;
import com.nhnacademy.exam.entity.QDepartmentEmployee;
import com.nhnacademy.exam.entity.QEmployee;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class DepartmentMemberRepositoryImpl extends QuerydslRepositorySupport implements DepartmentMemberCustom {
    public DepartmentMemberRepositoryImpl() {
        super(DepartmentMemberDto.class);
    }

    @Override
    public List<DepartmentMemberDto> departmentList(String departmentId) {
        QDepartment department = QDepartment.department;
        QDepartmentEmployee departmentEmployee = QDepartmentEmployee.departmentEmployee;
        QEmployee employee = QEmployee.employee;

        return from(department)
                .innerJoin(departmentEmployee)
                .on(department.id.eq(departmentEmployee.pk.departmentId))
                .innerJoin(employee)
                .on(departmentEmployee.pk.employeeId.eq(employee.id))
                .where(department.id.eq(departmentId))
                .select(Projections.constructor(DepartmentMemberDto.class,
                        Projections.constructor(DepartmentsDto.class, department.id, department.name),
                        Projections.constructor(EmployeesDto.class, employee.id, employee.name))).fetch();
    }
}
