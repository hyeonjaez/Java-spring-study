package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.dto.DepartmentDto;
import com.nhnacademy.exam.domain.dto.DepartmentMemberDto;
import com.nhnacademy.exam.domain.request.DepartmentModifyRequest;
import com.nhnacademy.exam.domain.request.DepartmentRegisterRequest;
import com.nhnacademy.exam.domain.response.DepartmentRegisterResponse;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.exception.MissingParameterException;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMemberRepository departmentMemberRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @Override
    @Transactional
    public DepartmentRegisterResponse create(DepartmentRegisterRequest departmentRegisterRequest) {
        if (departmentRepository.existsById(departmentRegisterRequest.getId())) {
            throw new DuplicateDepartmentIdException("부서 아이디 중복 : " + departmentRegisterRequest.getId());
        }


        Department department = new Department(departmentRegisterRequest.getId(), departmentRegisterRequest.getName());

        Department save = departmentRepository.save(department);
        if (Objects.equals(department, save)) {
            return new DepartmentRegisterResponse(save.getId());
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartment(String departmentId) {
        Optional<DepartmentDto> departmentById = departmentRepository.findDepartmentById(departmentId);
        if (departmentById.isPresent()) {
            return departmentById.get();
        } else {
            throw new DepartmentNotFoundException("department not found : " + departmentId);
        }
    }

    @Override
    @Transactional
    public boolean modifyDepartment(String departmentId, DepartmentModifyRequest departmentModifyRequest) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            Department target = department.get();
            Department modify = Department.builder().id(target.getId()).name(departmentModifyRequest.getName()).build();

            Department result = departmentRepository.save(modify);
            return Objects.equals(result, modify);
        } else {
            throw new DuplicateDepartmentIdException("department not found : " + departmentId);
        }
    }

    @Override
    @Transactional
    public boolean deleteDepartment(String departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Department target = department.get();
            departmentRepository.deleteById(target.getId());

            return !departmentRepository.existsById(target.getId());
        } else {
            throw new DuplicateDepartmentIdException("department not found : " + departmentId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentMemberDto> getAllDepartmentMember(String departmentId) {
        if (Objects.isNull(departmentId) || departmentId.isEmpty()) {
            throw new MissingParameterException(
                    "Required request parameter 'departmentIds' for method parameter type String is not present");
        }

        return departmentMemberRepository.departmentList(departmentId);
    }
}
