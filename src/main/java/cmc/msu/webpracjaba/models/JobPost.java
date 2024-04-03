package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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

    @Type(ListArrayType.class)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "duties", nullable = false)
    @NonNull
    private List<Duty> duties;

    @Override
    public Integer getId() {
        return this.job_post_id;
    }

    @Override
    public void setId(Integer id) {
        this.job_post_id = id;
    }
}
