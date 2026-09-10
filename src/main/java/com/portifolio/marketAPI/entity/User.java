package com.portifolio.marketAPI.entity;

import com.portifolio.marketAPI.entity.enums.UserRole;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;


    //CONSTRUCTORS
    public User() {
    }

    public User(String name, String password, UserRole userRole) {
        this.name = name;
        this.password = password;
        this.userRole = userRole;
    }


    //GETTERS
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Establishment getEstablishment() {
        return establishment;
    }


    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
