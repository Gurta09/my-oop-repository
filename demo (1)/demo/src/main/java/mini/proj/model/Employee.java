package mini.proj.model;

import lombok.Getter;
import lombok.Setter;
import mini.proj.enums.EmployeeCommunity;
import mini.proj.enums.EmployeeLevel;
import mini.proj.enums.EmployeeStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeLevel level;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeCommunity community;

    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

}
