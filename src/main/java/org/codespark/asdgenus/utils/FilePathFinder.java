package org.codespark.asdgenus.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FilePathFinder {

    @Value("${python.test.path}")
    private String testPath;

    @Value("${python.pre-porcessing}")
    private String preprocessingScript;

    @Value("${python.feature-extraction}")
    private String featureExtractionScript;

    @Value("${python.plot-eeg-signal}")
    private String plotEEGScript;

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

    @Value("${eeg.signal.base-path}")
    private String storageLocation;

    @Value("${venv.python}")
    private String venvLocation;

    public String getTestPythonScript() {
        return new File(testPath).getAbsolutePath();
    }

    public String getPreprocessPythonScript() {
        return new File(preprocessingScript).getAbsolutePath();
    }

    public String getFeatureExtractionPythonScript() {
        return new File(featureExtractionScript).getAbsolutePath();
    }

    public String getPlotEEGScript() {
        return new File(plotEEGScript).getAbsolutePath();
    }

    public String getPreprocessDataFile() {
        return new File(preprocessDataFile).getAbsolutePath();
    }

    public String getTestDataFile() {
        return new File(testDataFile).getAbsolutePath();
    }

    public String getTrainDataFile() {
        return new File(trainDataFile).getAbsolutePath();
    }

    public String getRandomForestModel() {
        return new File(randomForestModel).getAbsolutePath();
    }

    public String getLogisticRegressionModel() {
        return new File(logisticRegressionModel).getAbsolutePath();
    }

    public String getMlpModel() {
        return new File(mlpModel).getAbsolutePath();
    }

    public String getNaiveBayesModel() {
        return new File(naiveBayesModel).getAbsolutePath();
    }

    public String getStorageLocation() {
        return new File(storageLocation).getAbsolutePath();
    }

    public String getVenvLocation() {
        return new File(venvLocation).getAbsolutePath();
    }
}
