package mini.proj.model;

import lombok.Getter;
import lombok.Setter;

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
    private String level;
    @Column
    private String email;
    @Column
    private String community;
    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

}
