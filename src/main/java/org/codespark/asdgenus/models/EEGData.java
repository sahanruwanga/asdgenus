package org.codespark.asdgenus.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class EEGData {

    @TableGenerator(name = "eegdata", initialValue = 3500)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "eegData", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Result result;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Subject subject;

    private int numberOfChannels;
    private String[] channelNames;
    private String duration;
    private String recordedDate;
    private String dataLocation;
    private String signalLocation;

    public EEGData(int eegId, int numberOfChannels, String[] channelNames, String duration,
                   String recordedDate, String dataLocation, String signalLocation) {
        this.id = eegId;
        this.numberOfChannels = numberOfChannels;
        this.channelNames = channelNames;
        this.duration = duration;
        this.recordedDate = recordedDate;
        this.dataLocation = dataLocation;
        this.signalLocation = signalLocation;
    }

    public EEGData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }

    public String[] getChannelNames() {
        return channelNames;
    }

    public void setChannelNames(String[] channelNames) {
        this.channelNames = channelNames;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }

    public String getSignalLocation() {
        return signalLocation;
    }

    public void setSignalLocation(String signalLocation) {
        this.signalLocation = signalLocation;
    }
}
