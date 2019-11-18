package org.codespark.asdgenus.utils;

import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.models.Result;

public class ResultBuilder {

    private static ResultBuilder instance;
    private static final String RESULT_DESCRIPTION = "There is %1$s chance that the subject might have %2$s";

    private ResultBuilder() {
    }

    public static ResultBuilder getInstance() {

        if (instance == null)
            instance = new ResultBuilder();
        return instance;
    }

    public ResultDTO buildResultDTO(String predictionLabel) {

        ResultDTO resultDTO = new ResultDTO();
        setResultAndDescriptionBucketing(predictionLabel, resultDTO);
        resultDTO.setDateOfTaken(DateTimeGenerator.getInstance().getCurrentDateTime());
        return resultDTO;
    }

    private void setResultAndDescriptionBucketing(String predictionLabel, ResultDTO resultDTO) {

        switch (predictionLabel) {
            case ("n"):
                resultDTO.setResult("No-ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "68%", resultDTO.getResult()));
                return;
            case ("p"):
                resultDTO.setResult("Low Risk for ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "68%", resultDTO.getResult()));
                return;
            case ("l"):
                resultDTO.setResult("Mild ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "68%", resultDTO.getResult()));
                return;
            case ("h"):
                resultDTO.setResult("Severe ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "68%", resultDTO.getResult()));
                return;
            default:
        }
    }

    private void setResultAndDescription(String predictionLabel, ResultDTO resultDTO) {

        switch (predictionLabel) {
            case ("n"):
                resultDTO.setResult("No-ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", resultDTO.getResult()));
                return;
            case ("y"):
                resultDTO.setResult("ASD");
                resultDTO.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", resultDTO.getResult()));
                return;
            default:
        }
    }
}
