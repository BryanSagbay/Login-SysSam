package com.sam.LoginSysSam.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB-UserRegister", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_Name"})})
public  class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_Id")
    Integer id;

    @Size(max = 155, message="E-mail user have not more of 155 characters")
    @Column(name = "USER_email", nullable = false)
    String email;

    @NotBlank(message = "User name is obligatory")
    @Size(min = 2, max = 55, message="User name have not more of 55 characters")
    @Column(name = "USER_Name")
    String username;

    @Size(min = 2, max = 105, message="First name have not more of 105 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "The first name field can only contain letters, numbers, spaces, and hyphens.")
    @Column(name = "USER_FirstName", nullable = false)
    String first_name;

    @Size(max = 105, message="Last name have not more of 105 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "The last name field can only contain letters, numbers, spaces, and hyphens.")
    @Column(name = "USER_LastName", nullable = false)
    String last_name;

    @Size(max = 255, message="Password have not more of 255 characters")
    @Column(name = "USER_Password")
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}