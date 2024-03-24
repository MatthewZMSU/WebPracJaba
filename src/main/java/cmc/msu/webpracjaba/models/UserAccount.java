package cmc.msu.webpracjaba.models;

import cmc.msu.webpracjaba.Common;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "UserAccount")
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class UserAccount implements Common<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id", nullable = false)
    @NonNull
    private Integer user_account_id;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String first_name;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String last_name;

    @Column(name = "middle_name", nullable = true)
    @Nullable
    private String middle_name;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    @NonNull
    private UserType user_type_id;

    @Column(name = "registration_date", nullable = false)
    @NonNull
    private Timestamp registration_date;

    @Column(name = "login", nullable = false)
    @NonNull
    private String login;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;
}
