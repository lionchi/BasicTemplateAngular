package com.gavrilov.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "role")
public class Role extends BasicEntity {
    @Column(name = "rolename", nullable = false)
    private String rolename;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    @Nonnull
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
