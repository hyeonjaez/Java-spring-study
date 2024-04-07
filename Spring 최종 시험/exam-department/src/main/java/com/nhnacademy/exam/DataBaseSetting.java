package com.nhnacademy.exam;


import com.nhnacademy.exam.domain.EmployeeInfo;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentEmployee;
import com.nhnacademy.exam.entity.Employee;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.repository.EmployeeRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DataBaseSetting {

    private final DepartmentParserResolver departmentParserResolver;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;
    private final EmployeeRepository employeeRepository;

    public void dbSetting(String filename) throws IOException {
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(filename);
        Resource resource =
                (Resource) new PathMatchingResourcePatternResolver().getResource("classpath:" + "data/" + filename);

        List<EmployeeInfo> parsing = departmentParser.parsing(resource.getFile());

        for (EmployeeInfo e : parsing) {
            Employee employee = employeeRepository.findById(e.getId())
                    .orElseGet(
                            () -> employeeRepository.save(
                                    new Employee(e.getId(), e.getName())));

            Department department = departmentRepository.findById(e.getDepartmentId())
                    .orElseGet(
                            () -> departmentRepository.save(
                                    new Department(e.getDepartmentId(), e.getDepartmentName())));
            DepartmentEmployee departmentMember = new DepartmentEmployee(
                    new DepartmentEmployee.Pk(e.getId(), e.getDepartmentId()), department, employee);
            departmentMemberRepository.save(departmentMember);

        }
    }

}


