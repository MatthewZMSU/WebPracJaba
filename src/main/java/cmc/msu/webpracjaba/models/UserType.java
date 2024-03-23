package cmc.msu.webpracjaba.models;

import jakarta.persistence.*;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id", nullable = false)
    private int user_type_id;

    @Column(name = "user_type_name", nullable = false)
    private String user_type_name;

    @Column(name = "description", nullable = false)
    private String description;
}
