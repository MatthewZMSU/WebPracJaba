package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "JobPost")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class JobPost implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id", nullable = false)
    @NonNull
    private Integer job_post_id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "description", nullable = true)
    @Nullable
    private String description;

    @ElementCollection
    @Column(name = "duties", nullable = false)
    @NonNull
    private List<Integer> duties;

    @Override
    public Integer getId() {
        return this.job_post_id;
    }

    @Override
    public void setId(Integer id) {
        this.job_post_id = id;
    }
}
