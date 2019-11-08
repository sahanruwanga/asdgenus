package org.codespark.asdgenus.utils;

import org.codespark.asdgenus.models.Result;

public class ResultBuilder {

    private static ResultBuilder instance;
    private static final String RESULT_DESCRIPTION = "There is %1$s possibility that the subject might have %2$s";

    private ResultBuilder() {
    }

    public static ResultBuilder getInstance() {

        if (instance == null)
            instance = new ResultBuilder();
        return instance;
    }

    public Result buildResult(String predictionLabel) {

        Result result = new Result();
        setResultAndDescription(predictionLabel, result);
        result.setDateOfTaken(DateTimeGenerator.getInstance().getCurrentDateTime());
        return result;
    }

    private void setResultAndDescriptionBucketing(String predictionLabel, Result result) {

        switch (predictionLabel) {
            case ("n"):
                result.setResult("No-ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            case ("p"):
                result.setResult("Potential ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            case ("l"):
                result.setResult("Low ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            case ("h"):
                result.setResult("High ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            default:
        }
    }

    private void setResultAndDescription(String predictionLabel, Result result) {

        switch (predictionLabel) {
            case ("n"):
                result.setResult("No-ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            case ("y"):
                result.setResult("ASD");
                result.setResultDescription(String.format(RESULT_DESCRIPTION, "94%", result.getResult()));
                return;
            default:
        }
    }
}
