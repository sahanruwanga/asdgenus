package org.codespark.asdgenus.services.feature_extraction;

import org.codespark.asdgenus.controllers.ResultController;
import org.codespark.asdgenus.utils.FilePathFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FeatureExtraction {

    @Autowired
    private FilePathFinder filePathFinder;

    public String extractFeatures() {

        String pyPath = filePathFinder.getFeatureExtractionPythonScript();
        String preprocessDataFile = filePathFinder.getPreprocessDataFile();
        String testDataFile = filePathFinder.getTestDataFile();
        String venvPath = filePathFinder.getVenvLocation();
        String ret;
        ArrayList<String> prediction = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder().command(venvPath, pyPath, preprocessDataFile, testDataFile);

        Process process;
        try {
            process = processBuilder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((ret = in.readLine()) != null) {
                prediction.add(ret.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prediction.get(0);
    }
}
