package org.codespark.asdgenus.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn
    @JsonBackReference
    private EEGData eegData;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Subject subject;

    private String result;
    private String resultDescription;
    private String dateOfTaken;

    public Result(int resultId, String result, String resultDescription, String dateOfTaken) {
        this.id = resultId;
        this.result = result;
        this.resultDescription = resultDescription;
        this.dateOfTaken = dateOfTaken;
    }

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public String getDateOfTaken() {
        return dateOfTaken;
    }

    public void setDateOfTaken(String dateOfTaken) {
        this.dateOfTaken = dateOfTaken;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public EEGData getEegData() {
        return eegData;
    }

    public void setEegData(EEGData eegData) {
        this.eegData = eegData;
    }
}
