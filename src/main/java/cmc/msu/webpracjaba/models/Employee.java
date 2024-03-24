package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Employee implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    @NonNull
    private Integer employee_id;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String first_name;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String last_name;

    @Column(name = "middle_name", nullable = true)
    @Nullable
    private String middle_name;

    @Column(name = "address", nullable = false)
    @NonNull
    private String address;

    @Column(name = "education", nullable = false)
    @NonNull
    private String education;

    @Override
    public Integer getId() {
        return this.employee_id;
    }

    @Override
    public void setId(Integer id) {
        this.employee_id = id;
    }
}
