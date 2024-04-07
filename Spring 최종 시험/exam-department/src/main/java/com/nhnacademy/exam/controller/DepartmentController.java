package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.domain.dto.DepartmentDto;
import com.nhnacademy.exam.domain.request.DepartmentModifyRequest;
import com.nhnacademy.exam.domain.request.DepartmentRegisterRequest;
import com.nhnacademy.exam.domain.response.DepartmentRegisterResponse;
import com.nhnacademy.exam.service.DepartmentService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentRegisterResponse> registerDepartment(
            @Valid @RequestBody DepartmentRegisterRequest departmentRegisterRequest) {
        DepartmentRegisterResponse departmentRegisterResponse = departmentService.create(departmentRegisterRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(departmentRegisterResponse);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentId") String departmentCode) {
        DepartmentDto department = departmentService.getDepartment(departmentCode);

        return ResponseEntity.ok().body(department);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Void> modifyDepartment(@PathVariable("departmentId") String departmentCode,
                                                 @Valid @RequestBody
                                                 DepartmentModifyRequest departmentModifyRequest) {
        departmentService.modifyDepartment(departmentCode, departmentModifyRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId") String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
