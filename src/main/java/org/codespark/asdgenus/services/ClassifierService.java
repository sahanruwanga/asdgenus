package org.codespark.asdgenus.services;

import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.services.classification.Classification;
import org.codespark.asdgenus.services.pre_processing.EEGDataPreprocessing;
import org.codespark.asdgenus.services.feature_extraction.FeatureExtraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClassifierService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private EEGDataPreprocessing eegDataPreprocessing;

    @Autowired
    private FeatureExtraction featureExtraction;

    @Autowired
    private Classification classification;

    public Result getPrediction(String eegPath) {

        eegDataPreprocessing.preProcessEEG(eegPath);
        String features = featureExtraction.extractFeatures();
        Result result = classification.classifyASD("features");
        return result;
    }

    private String saveAndReturnLocation(MultipartFile[] files) {
        String vhdrFilePath = "";
        for (int i = 0; i < files.length; i++) {
            if (files[i].getOriginalFilename().endsWith("vhdr"))
                vhdrFilePath = fileStorageService.storeFile(files[i]);
            else
                fileStorageService.storeFile(files[i]);
        }
        return vhdrFilePath;
    }
}
