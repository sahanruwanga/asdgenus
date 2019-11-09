package org.codespark.asdgenus.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<EEGData> eegData;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Result> results;

    private int age;
    private String name;
    private String gender;

    public Subject(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<EEGData> getEegData() {
        return eegData;
    }

    public void setEegData(Set<EEGData> eegData) {
        this.eegData = eegData;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
}
