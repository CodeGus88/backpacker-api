package com.backpackerapi.backpacker.security.entity;

import com.backpackerapi.backpacker.models.BaseModel;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
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

    public User(@NotNull String name, @NotNull String username, @NotNull String email, @NotNull String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
