package org.codespark.asdgenus.dtos;

public class ResultSavingDTO {

    private int resultId;
    private String result;
    private String resultDescription;
    private String dateOfTaken;

    private int eegId;
    private int numberOfChannels;
    private String[] channelNames;
    private String duration;
    private String recordedDate;
    private String dataLocation;
    private String signalLocation;

    private int subjectId;
    private int age;
    private String subjectName;
    private String subjectGender;

    public ResultSavingDTO() {
    }

    public ResultSavingDTO(int resultId, String result, String resultDescription, String dateOfTaken,
                           int eegId, int numberOfChannels, String[] channelNames, String duration,
                           String recordedDate, String dataLocation, String signalLocation, int subjectId,
                           int age, String subjectName, String subjectGender) {
        this.resultId = resultId;
        this.result = result;
        this.resultDescription = resultDescription;
        this.dateOfTaken = dateOfTaken;
        this.eegId = eegId;
        this.numberOfChannels = numberOfChannels;
        this.channelNames = channelNames;
        this.duration = duration;
        this.recordedDate = recordedDate;
        this.dataLocation = dataLocation;
        this.signalLocation = signalLocation;
        this.subjectId = subjectId;
        this.age = age;
        this.subjectName = subjectName;
        this.subjectGender = subjectGender;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
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

    public int getEegId() {
        return eegId;
    }

    public void setEegId(int eegId) {
        this.eegId = eegId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectGender() {
        return subjectGender;
    }

    public void setSubjectGender(String subjectGender) {
        this.subjectGender = subjectGender;
    }
}
