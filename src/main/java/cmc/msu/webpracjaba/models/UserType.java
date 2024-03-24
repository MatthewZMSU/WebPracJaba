package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserType")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class UserType implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id", nullable = false)
    @NonNull
    private Integer user_type_id;

    @Column(name = "user_type_name", nullable = false)
    @NonNull
    private String user_type_name;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;
}
