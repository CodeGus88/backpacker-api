package com.backpackerapi.backpacker.security.entity;

import com.backpackerapi.backpacker.models.BaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {

    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String tokenPassword;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_uuid"),
    inverseJoinColumns = @JoinColumn(name = "role_uuid"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotNull String name, @NotNull String username, @NotNull String email, @NotNull String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
