package org.codespark.asdgenus.services.learning_models;

import org.codespark.asdgenus.utils.FilePathFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class LearningModel {

    @Autowired
    private FilePathFinder filePathFinder;

    // Learning model names
    private static final String RANDOM_FOREST = "RandomForest";
    private static final String MULTILAYER_PERCEPTRON = "MultilayerPerceptron";
    private static final String NAIVE_BAYES = "NaiveBayes";
    private static final String LOGISTIC = "Logistic";

    /**
     * Save the given learning model in the specific location
     *
     * @param classifier
     * @param classifierType
     */
    public void saveModel(Classifier classifier, String classifierType) {
        try {
            if (classifierType.equals(RANDOM_FOREST))
                SerializationHelper.write(filePathFinder.getRandomForestModel(), classifier);
            else if (classifierType.equals(MULTILAYER_PERCEPTRON))
                SerializationHelper.write(filePathFinder.getMlpModel(), classifier);
            else if (classifierType.equals(NAIVE_BAYES))
                SerializationHelper.write(filePathFinder.getNaiveBayesModel(), classifier);
            else if (classifierType.equals(LOGISTIC))
                SerializationHelper.write(filePathFinder.getLogisticRegressionModel(), classifier);
        } catch (Exception ex) {
            Logger.getLogger(LearningModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Build the required learning model with the training set inside the system
     *
     * @param classifierType
     * @return
     */
    public Classifier buildModel(String classifierType) {
        Classifier classifier = null;
        try {
            DataSource trainData = new DataSource(filePathFinder.getTrainDataFile());
            Instances train = trainData.getDataSet();
            train.setClassIndex(train.numAttributes() - 1);

            if (classifierType.equals(RANDOM_FOREST))
                classifier = new RandomForest();
            else if (classifierType.equals(MULTILAYER_PERCEPTRON))
                classifier = AbstractClassifier.forName("weka.classifiers.functions.MultilayerPerceptron", new String[]{"-L", "0.30858093642150297", "-M", "0.22943139932442275", "-B", "-H", "i", "-C", "-R", "-S", "1"});
            else if (classifierType.equals(NAIVE_BAYES))
                classifier = new NaiveBayes();
            else if (classifierType.equals(LOGISTIC)) {
                classifier = new Logistic();
            }
            classifier.buildClassifier(train);
        } catch (Exception ex) {
            Logger.getLogger(LearningModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classifier;
    }

    /**
     * First check whether the required learning model is already exist in the saving location and if not there, build
     * the model and return at the end
     *
     * @param classifierType
     * @return
     */
    public Classifier getModel(String classifierType) {

        Classifier classifier = null;
        try {
            if (classifierType.equals(MULTILAYER_PERCEPTRON))
                classifier = (MultilayerPerceptron) SerializationHelper.read(filePathFinder.getMlpModel());
            else if (classifierType.equals(RANDOM_FOREST))
                classifier = (RandomForest) SerializationHelper.read(filePathFinder.getRandomForestModel());
            else if (classifierType.equals(NAIVE_BAYES))
                classifier = (NaiveBayes) SerializationHelper.read(filePathFinder.getNaiveBayesModel());
            else if (classifierType.equals(LOGISTIC))
                classifier = (Logistic) SerializationHelper.read(filePathFinder.getLogisticRegressionModel());
        } catch (Exception ex) {
//            Logger.getLogger(LearningModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (classifier == null) {
            classifier = buildModel(classifierType);
            saveModel(classifier, classifierType);
        }
        return classifier;
    }

    /**
     * This is for evaluating the model to ensure the system is having a better prediction accuracy. This is not
     * available for externals
     *
     * @param classifierType
     */
    public void evaluateModel(String classifierType) {

        try {
            DataSource trainDS = new DataSource(filePathFinder.getTrainDataFile());
            Instances train = trainDS.getDataSet();
            train.setClassIndex(train.numAttributes() - 1);

            Evaluation eval = new Evaluation(train);
            eval.crossValidateModel(getModel(classifierType), train, 17, new Random(1));
            System.out.println("Classifier : \n" + getModel(classifierType));
            System.out.println(eval.toSummaryString("\nEvaluation Result: \n", false));
            System.out.println(eval.predictions());

        } catch (IOException ex) {
            Logger.getLogger(LearningModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LearningModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
