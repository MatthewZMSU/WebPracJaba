package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class Department implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    @NonNull
    private Integer department_id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "description", nullable = true)
    @Nullable
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director", nullable = false)
    @NonNull
    private Employee director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_department", nullable = true)
    @Nullable
    private Department head_department;
}
