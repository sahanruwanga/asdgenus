package org.codespark.asdgenus.services.visualization;

import org.codespark.asdgenus.controllers.ResultController;
import org.codespark.asdgenus.utils.FilePathFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VisualizationService {

    @Autowired
    private FilePathFinder filePathFinder;

    public void plotHeadLocations() {
    }

    public ArrayList<String> plotEEGSignal(int uid, String eegPath) {

        String pyPath = filePathFinder.getPlotEEGScript();
        String signalLocation = filePathFinder.getStorageLocation() + "/" +
                uid + "_" + new Random().nextInt(1000000) + ".png";
        String venvPath = filePathFinder.getVenvLocation();

        String ret;
        ArrayList<String> temp = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder().command(venvPath, pyPath, eegPath,
                signalLocation);

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
        if (temp.size() != 0 && temp.get(0).equals("ok")) {
            temp.add(signalLocation);
            return temp;
        }
        else
            return null;
    }

    public String constructImage(String imagePath){
        String imgPath = null;
        File image = new File(imagePath);
        String encodeBase64;
        try {
            FileInputStream fileInputStream = new FileInputStream(image);
            byte[] bytes = new byte[(int)image.length()];
            fileInputStream.read(bytes);
            encodeBase64 = Base64.getEncoder().encodeToString(bytes);
            imgPath = "data:image/png;base64,"+encodeBase64;
            fileInputStream.close();
        } catch (Exception e) {
        }

        return imgPath;
    }
}
