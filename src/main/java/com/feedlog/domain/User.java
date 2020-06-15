package com.feedlog.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String roles;
    private String password;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile userProfile;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        enabled = true;
        roles = "USER";
    }

    public User(String username, String roles, String password, boolean enabled) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String roles, String password, boolean enabled, UserProfile userProfile) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.userProfile = userProfile;
        this.enabled = enabled;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<String> getRolesList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userProfile, user.userProfile);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, username, roles, password, userProfile);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + roles + '\'' +
                ", password='" + password + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }
}
