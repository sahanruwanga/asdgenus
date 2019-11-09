package org.codespark.asdgenus.dtos;

public class ResultDTO {

    private int id;
    private int eegId;
    private int subjectId;
    private String result;
    private String resultDescription;
    private String dateOfTaken;

    public ResultDTO(int id, int eegId, int subjectId, String result, String resultDescription, String dateOfTaken) {
        this.id = id;
        this.eegId = eegId;
        this.subjectId = subjectId;
        this.result = result;
        this.resultDescription = resultDescription;
        this.dateOfTaken = dateOfTaken;
    }

    public ResultDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEegId() {
        return eegId;
    }

    public void setEegId(int eegId) {
        this.eegId = eegId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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
}
