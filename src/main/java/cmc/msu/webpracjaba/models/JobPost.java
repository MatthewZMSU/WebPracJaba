package cmc.msu.webpracjaba.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "JobPost")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id", nullable = false)
    private int job_post_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    @Nullable
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "duties", nullable = false)
    private List<Duty> duties;
}
