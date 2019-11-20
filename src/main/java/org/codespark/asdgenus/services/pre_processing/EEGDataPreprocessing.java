package org.codespark.asdgenus.services.pre_processing;

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
public class EEGDataPreprocessing {

    @Autowired
    private FilePathFinder filePathFinder;

    public void preProcessEEG(String eegFilePath) {

        String pyPath = filePathFinder.getPreprocessPythonScript();
        String preprocessDataFile = filePathFinder.getPreprocessDataFile();
        String venvPath = filePathFinder.getVenvLocation();
        String ret;
        ArrayList<String> prediction = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder(venvPath, pyPath, eegFilePath, preprocessDataFile);

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
    }
}
