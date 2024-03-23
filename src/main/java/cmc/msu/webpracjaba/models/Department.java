package cmc.msu.webpracjaba.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private int department_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    @Nullable
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director", nullable = false)
    private Employee director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_department", nullable = true)
    @Nullable
    private Department head_department;
}
