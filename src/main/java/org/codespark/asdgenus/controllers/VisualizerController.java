package org.codespark.asdgenus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/visualizer")
public class VisualizerController {

    @PostMapping("/plot/{plotType}")
    public String plotVisualization(@RequestParam String plotType) {
        return "Plot type : " + plotType;
    }
}
