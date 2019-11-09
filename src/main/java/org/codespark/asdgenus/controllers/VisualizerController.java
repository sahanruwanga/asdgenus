package org.codespark.asdgenus.controllers;

import org.springframework.web.bind.annotation.*;

@RestController("/visualizer")
@CrossOrigin(origins = "*")
public class VisualizerController {

    @PostMapping("/plot/{plotType}")
    public String plotVisualization(@RequestHeader("uid") int uid, @RequestParam String plotType) {
        return "Plot type : " + plotType;
    }
}
