package cmc.msu.webpracjaba.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Duty")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "duty_id", nullable = false)
    private int duty_id;

    @Column(name = "description", nullable = false)
    private String description;
}
