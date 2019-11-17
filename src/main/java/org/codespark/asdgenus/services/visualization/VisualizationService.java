package org.codespark.asdgenus.services.visualization;

import org.codespark.asdgenus.controllers.ResultController;
import org.codespark.asdgenus.utils.DateTimeGenerator;
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
public class VisualizationService {

    @Autowired
    private FilePathFinder filePathFinder;

    public void plotHeadLocations() {
    }

    public String plotEEGSignal(int uid, String eegPath) {

        String pyPath = filePathFinder.getPlotEEGScript();
        String signalLocation = filePathFinder.getEegSignalBasePath() + '/' +
                uid + '/' + DateTimeGenerator.getInstance().getCurrentDateTime() + ".jpg";
        String ret;
        ArrayList<String> temp = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder("python", pyPath, eegPath, signalLocation);

        Process process;
        try {
            process = processBuilder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((ret = in.readLine()) != null) {
                temp.add(ret.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if (temp.size() != 0 && temp.get(0).equals("ok"))
//            return signalLocation;
//        else
        return filePathFinder.getEegSignalBasePath() + "/1.jpg";
    }
}
