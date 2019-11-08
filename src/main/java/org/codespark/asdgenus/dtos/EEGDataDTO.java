package org.codespark.asdgenus.dtos;

public class EEGDataDTO {

    private int id;
    private int numberOfChannels;
    private int subjectId;
    private String[] channelNames;
    private String duration;
    private String recordedDate;
    private String dataLocation;

    public EEGDataDTO(int id, int numberOfChannels, int subjectId, String[] channelNames, String duration, String recordedDate, String dataLocation) {
        this.id = id;
        this.numberOfChannels = numberOfChannels;
        this.subjectId = subjectId;
        this.channelNames = channelNames;
        this.duration = duration;
        this.recordedDate = recordedDate;
        this.dataLocation = dataLocation;
    }

    public EEGDataDTO() {
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }
}
