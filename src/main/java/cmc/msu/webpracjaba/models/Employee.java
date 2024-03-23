package cmc.msu.webpracjaba.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private int employee_id;

    @Column(name = "first_name", nullable = false)  // TODO: to constraint the length
    private String first_name;

    @Column(name = "second_name", nullable = false)
    private String second_name;

    @Column(name = "middle_name", nullable = true)
    @Nullable
    private String middle_name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "education", nullable = false)
    private String education;
}
