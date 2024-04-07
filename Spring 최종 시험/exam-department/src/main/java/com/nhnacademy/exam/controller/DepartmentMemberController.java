package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.domain.dto.DepartmentMemberDto;
import com.nhnacademy.exam.service.DepartmentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentMemberController {

    private final DepartmentService departmentService;

    public DepartmentMemberController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department-members")
    public ResponseEntity<List<DepartmentMemberDto>> getAllDepartmentMember(
            @RequestParam("departmentIds") String departmentId) {
        List<DepartmentMemberDto> allDepartmentMember = departmentService.getAllDepartmentMember(departmentId);
        return ResponseEntity.ok().body(allDepartmentMember);
    }
}
