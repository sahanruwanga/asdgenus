package org.codespark.asdgenus.services.classification;

import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.services.learning_models.LearningModel;
import org.codespark.asdgenus.utils.FilePathFinder;
import org.codespark.asdgenus.utils.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class Classification {

    private static final String MULTILAYER_PERCEPTRON = "MultilayerPerceptron";

    @Autowired
    private FilePathFinder filePathFinder;

    @Autowired
    private LearningModel learningModel;

    public ResultDTO classifyASD(String features) {

//        String testSet[] = features.substring(1, features.length()-1).split(",");

        DataSource testData = null;
        String prediction = null;
        double output;
        try {
            testData = new DataSource(filePathFinder.getTestDataFile());
            Instances test = testData.getDataSet();
            test.setClassIndex(test.numAttributes() - 1);

            MultilayerPerceptron mlp = (MultilayerPerceptron) learningModel.getModel(MULTILAYER_PERCEPTRON);
            // Getting the last row of the csv as the test data
            Instance inst = test.instance(test.numInstances()-1);
            output = mlp.classifyInstance(inst);
            prediction = test.classAttribute().value((int) output);
        } catch (Exception ignored) {
        }
        return ResultBuilder.getInstance().buildResultDTO(prediction);
    }
}
