package cmc.msu.webpracjaba.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "UserAccount")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id", nullable = false)
    private int user_account_id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "middle_name", nullable = true)
    @Nullable
    private String middle_name;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType user_type_id;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registration_date;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
