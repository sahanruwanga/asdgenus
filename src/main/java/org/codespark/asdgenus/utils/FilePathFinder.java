package org.codespark.asdgenus.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilePathFinder {

    @Value("${python.test.path}")
    private String testPath;

    @Value("${python.pre-porcessing}")
    private String preprocessingScript;

    @Value("${python.feature-extraction}")
    private String featureExtractionScript;

    @Value("${data.preprocess}")
    private String preprocessDataFile;

    @Value("${data.test}")
    private String testDataFile;

    @Value("${data.train}")
    private String trainDataFile;

    @Value("${model.random-forest}")
    private String randomForestModel;

    @Value("${model.logistic-regression}")
    private String logisticRegressionModel;

    @Value("${model.mlp}")
    private String mlpModel;

    @Value("${model.nb}")
    private String naiveBayesModel;

    public String getTestPythonScript() {
        return testPath;
    }

    public String getPreprocessPythonScript() {
        return preprocessingScript;
    }

    public String getFeatureExtractionPythonScript() {
        return featureExtractionScript;
    }

    public String getPreprocessDataFile() {
        return preprocessDataFile;
    }

    public String getTestDataFile() {
        return testDataFile;
    }

    public String getTrainDataFile() {
        return trainDataFile;
    }

    public String getRandomForestModel() {
        return randomForestModel;
    }

    public String getLogisticRegressionModel() {
        return logisticRegressionModel;
    }

    public String getMlpModel() {
        return mlpModel;
    }

    public String getNaiveBayesModel() {
        return naiveBayesModel;
    }
}
