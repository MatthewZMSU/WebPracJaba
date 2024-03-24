package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Duty")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class Duty implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "duty_id", nullable = false)
    @NonNull
    private Integer duty_id;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;
}
