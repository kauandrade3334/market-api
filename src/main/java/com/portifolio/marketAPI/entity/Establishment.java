package com.portifolio.marketAPI.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "establishments")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "establishment")
    private final List<User> userList = new ArrayList<>();


    //CONSTRUCTORS
    public Establishment() {
    }

    public Establishment(String cnpj, String name, String password) {
        this.cnpj = cnpj;
        this.name = name;
        this.password = password;
    }


    //GETTERS
    public String getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getUserList() {
        return userList;
    }


    //SETTERS
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //LIST METHODS
    public void addUser(User user) {
        this.userList.add(user);
        user.setEstablishment(this);
    }
    public void removeUser(User user) {
        this.userList.remove(user);
        user.setEstablishment(null);
    }
}
