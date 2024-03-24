package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "EmployeeInfo")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class EmployeeInfo implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_info_id", nullable = false)
    @NonNull
    private Integer employee_info_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @NonNull
    private Employee employee_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id", nullable = false)
    @NonNull
    private JobPost job_post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @NonNull
    private Department department_id;

    @Column(name = "start_date", nullable = false)
    @NonNull
    private Timestamp start_date;

    @Column(name = "end_date", nullable = true)
    @Nullable
    private Timestamp end_date;
}
