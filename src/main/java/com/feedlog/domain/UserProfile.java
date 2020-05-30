package com.feedlog.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    private String fullName;
    private String countryCode;
    private String gender;
    private String about;

    @OneToMany(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "userProfile")
    private List<Post> post;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public UserProfile(){
    }

    public UserProfile(String fullName, String countryCode, String gender, String about) {
        this.fullName = fullName;
        this.countryCode = countryCode;
        this.gender = gender;
        this.about = about;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return id == that.id &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(about, that.about) &&
                Objects.equals(post, that.post) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, countryCode, gender, about, post, user);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", gender='" + gender + '\'' +
                ", about='" + about + '\'' +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
