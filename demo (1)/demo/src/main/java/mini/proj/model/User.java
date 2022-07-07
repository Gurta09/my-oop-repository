package mini.proj.model;

import lombok.Getter;
import lombok.Setter;
import mini.proj.enums.UserRole;
import mini.proj.enums.UserStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "User_Object")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
