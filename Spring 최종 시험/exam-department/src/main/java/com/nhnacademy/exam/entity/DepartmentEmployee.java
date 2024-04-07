package com.nhnacademy.exam.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "department_employee")
public class DepartmentEmployee {

    @EmbeddedId
    private Pk pk;


    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;

    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;


    @Embeddable
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "department_id")
        String departmentId;

        @Column(name = "employee_id")
        String employeeId;
    }
}
