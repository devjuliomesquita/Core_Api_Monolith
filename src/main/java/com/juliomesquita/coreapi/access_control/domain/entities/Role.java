package com.juliomesquita.coreapi.access_control.domain.entities;

import com.juliomesquita.coreapi.access_control.domain.enums.PermissionsSystem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id", nullable = false)
    private UUID id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissions")
    private Set<PermissionsSystem> permissions;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(description, role.description) && Objects.equals(users, role.users) && Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, users, permissions);
    }

    public List<SimpleGrantedAuthority> getAuthoraties(){
        List<SimpleGrantedAuthority> permissionsList = new java.util.ArrayList<>(this.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .toList());
        permissionsList.add(new SimpleGrantedAuthority("ROLE_" + this.name));
        return permissionsList;
    }
}
